package tech.mateuslll.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import tech.mateuslll.core.domain.User;

public record CreateUserReq(@NotBlank @Length(max = 150) String email,
                            @NotBlank @Length(min = 8, max = 64) String password,
                            @NotBlank @Length(min = 5, max = 50) String nickname) {

    public User toDomain() {
        return new User(email, password, nickname);
    }
}
