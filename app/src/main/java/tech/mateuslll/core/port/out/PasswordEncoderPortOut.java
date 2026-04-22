package tech.mateuslll.core.port.out;

public interface PasswordEncoderPortOut {

    String encode(String password);

    boolean matches(String rawPassword, String encodedPassword);
}
