package tech.mateuslll.core.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tech.mateuslll.core.domain.User;
import tech.mateuslll.core.exception.UserAlreadyExistException;
import tech.mateuslll.core.port.in.CreaterUserPortIn;
import tech.mateuslll.core.port.out.PasswordEncoderPortOut;
import tech.mateuslll.core.port.out.UserRepositoryPortOut;

@Component
public class CreateUserUseCase implements CreaterUserPortIn {
    private static final Logger log = LoggerFactory.getLogger(CreateUserUseCase.class);
    private final UserRepositoryPortOut userRepositoryPortOut;
    private final PasswordEncoderPortOut passwordEncoderPortOut;

    public CreateUserUseCase(UserRepositoryPortOut userRepositoryPortOut, PasswordEncoderPortOut passwordEncoderPortOut) {
        this.userRepositoryPortOut = userRepositoryPortOut;
        this.passwordEncoderPortOut = passwordEncoderPortOut;
    }

    @Override
    public User execute(User user) {

        log.info("Creating user {}", user.getEmail());

        var userByEmail = userRepositoryPortOut.findByEmail(user.getEmail());

        if (userByEmail.isPresent()) {
            throw new UserAlreadyExistException();
        }

        user.encodePassword(passwordEncoderPortOut);

        var userCreated = userRepositoryPortOut.save(user);

        log.info("User created {}", user.getUserId());

        return userCreated;
    }
}
