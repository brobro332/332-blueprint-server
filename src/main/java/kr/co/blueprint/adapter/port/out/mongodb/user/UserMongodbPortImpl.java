package kr.co.blueprint.adapter.port.out.mongodb.user;

import kr.co.blueprint.domain.entity.user.UserDocument;
import kr.co.blueprint.domain.port.out.mongodb.user.UserMongodbPort;
import kr.co.blueprint.infrastructure.mongodb.repository.user.UserMongodbRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserMongodbPortImpl implements UserMongodbPort {
    private final UserMongodbRepository repository;

    @Override
    public void save(UserDocument userDocument) {
        repository.save(userDocument);
    }

    @Override
    public void delete(UserDocument userDocument) {
        repository.delete(userDocument);
    }
}