package kr.co.blueprint.domain.port.out.mongodb.user;

import kr.co.blueprint.domain.entity.user.UserDocument;

public interface UserMongodbPort {
    void save(UserDocument user);
    void delete(UserDocument user);
}
