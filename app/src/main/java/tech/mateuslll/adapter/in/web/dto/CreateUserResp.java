package tech.mateuslll.adapter.in.web.dto;

import tech.mateuslll.core.domain.User;

import java.time.LocalDateTime;

public record CreateUserResp(String userId, LocalDateTime createdAt) {

    public static CreateUserResp fromDomain (User user){
        return new CreateUserResp(user.getUserId().toString(), user.getCreatedAt());
    }
}
