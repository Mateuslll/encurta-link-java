package tech.mateuslll.adapter.in.web.dto;

import tech.mateuslll.core.domain.User;

public record CreateUserReq(String email, String password, String nickname) {

    public User toDomain(){
        return new User (email, password, nickname);
    }
}
