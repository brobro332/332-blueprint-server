package kr.co.samsami_hub.websocket.listener;

import ch.qos.logback.core.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketSessionListener {
    private final Map<String, String> sessions = new ConcurrentHashMap<>();

    @Value("${jwt.secret-key}")
    private String secretKey;

    /**
     * 세션 연결 이벤트 리스너
     * @param event
     */
    @EventListener
    public void handelSessionConnected(SessionConnectedEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        List<String> authenticationHeader = accessor.getNativeHeader("Authorization");
        if (authenticationHeader != null && !authenticationHeader.isEmpty()) {
            String token = authenticationHeader.getFirst().replace("Bearer ", "");
            String userId = extractUserIdFromToken(token);

            if (StringUtil.isNullOrEmpty(userId)) sessions.put(userId, accessor.getSessionId());
        }
    }

    /**
     * 세션 연결 해제 시 이벤트 리스너
     * @param event
     */
    @EventListener
    public void handleSessionDisconnected(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        List<String> authenticationHeader = accessor.getNativeHeader("Authorization");
        if (authenticationHeader != null && !authenticationHeader.isEmpty()) {
            String token = authenticationHeader.getFirst().replace("Bearer ", "");
            String userId = extractUserIdFromToken(token);

            if (StringUtil.isNullOrEmpty(userId)) sessions.remove(userId);
        }
    }

    /**
     * 토큰 추출 및 사용자 아이디 반환
     * @param token
     * @return
     */
    public String extractUserIdFromToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return claims.getSubject();
        } catch (SignatureException e) {
            throw new RuntimeException("토큰이 만료되었습니다.");
        }
    }
}
