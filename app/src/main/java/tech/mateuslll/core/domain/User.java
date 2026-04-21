package tech.mateuslll.core.domain;

import tech.mateuslll.core.port.out.PasswordEncoderPortOut;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {

    private UUID userId;
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String email, String password, String nickname) {
        this.userId = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public User(UUID userId, String email, String nickname, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public void encodePassword(PasswordEncoderPortOut passwordEncoderPortOut) {
        this.password = passwordEncoderPortOut.encode(this.password);
    }
}
