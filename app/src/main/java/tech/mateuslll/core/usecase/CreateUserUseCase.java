package tech.mateuslll.core.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tech.mateuslll.core.domain.User;
import tech.mateuslll.core.port.in.CreaterUserPortIn;
import tech.mateuslll.core.port.out.UserRepositoryPortOut;

@Component
public class CreateUserUseCase implements CreaterUserPortIn {
    private static final Logger log = LoggerFactory.getLogger(CreateUserUseCase.class);
    private final UserRepositoryPortOut userRepositoryPortOut;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CreateUserUseCase(UserRepositoryPortOut userRepositoryPortOut, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepositoryPortOut = userRepositoryPortOut;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User execute(User user) {

        user.encodePassword(bCryptPasswordEncoder);

        log.info("Creating user {}", user.getEmail());

        var userCreated = userRepositoryPortOut.save(user);

        log.info("User created {}", user.getUserId());

        return userCreated;
    }
}
