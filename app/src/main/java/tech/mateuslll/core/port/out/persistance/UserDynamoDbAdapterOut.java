package tech.mateuslll.core.port.out.persistance;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import tech.mateuslll.core.domain.User;
import tech.mateuslll.core.port.out.UserRepositoryPortOut;

import java.util.Optional;

import static tech.mateuslll.config.Constants.EMAIL_INDEX;

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

    @Override
    public Optional<User> findByEmail(String email) {

        var conditionalKey = QueryConditional
                .keyEqualTo(k -> k.partitionValue(AttributeValue.builder().s(email).build()));

        var queryRequest = QueryEnhancedRequest.builder()
                        .queryConditional(conditionalKey).build();

        var result = dynamoDbTemplate.query(queryRequest, UserEntity.class, EMAIL_INDEX);

        return result.stream()
                .flatMap(userEntityPage -> userEntityPage.items().stream())
                .map(UserEntity::toDomain)
                .findFirst();

    }
}
