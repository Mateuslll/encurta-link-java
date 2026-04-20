package tech.mateuslll.core.port.out.persistance;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.stereotype.Component;
import tech.mateuslll.core.domain.User;
import tech.mateuslll.core.port.out.UserRepositoryPortOut;

@Component
public class UserDynamoDbAdapterOut implements UserRepositoryPortOut {

    private DynamoDbTemplate dynamoDbTemplate;

    public UserDynamoDbAdapterOut(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserEntity.fromDomain(user);
        dynamoDbTemplate.save(entity);
        return user;
    }
}
