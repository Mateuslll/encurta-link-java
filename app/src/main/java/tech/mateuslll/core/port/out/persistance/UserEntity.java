package tech.mateuslll.core.port.out.persistance;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import tech.mateuslll.config.TableName;
import tech.mateuslll.core.domain.User;

import java.time.LocalDateTime;
import java.util.UUID;

@DynamoDbBean
@TableName(name = "tb_users")
public class UserEntity {

    private UUID userId;
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserEntity fromDomain(User user) {
        var entity = new UserEntity();

        entity.setUserId(user.getUserId());
        entity.setEmail(user.getEmail());
        entity.setNickname(user.getNickname());
        entity.setPassword(user.getPassword());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());

        return entity;
    }

    //aqui no tem problema expor os getters e setters m prtica seria expor o domain

    @DynamoDbPartitionKey
    @DynamoDbAttribute("user_id")
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "email-index")
    @DynamoDbAttribute("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
