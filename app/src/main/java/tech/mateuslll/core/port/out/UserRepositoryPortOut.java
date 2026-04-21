package tech.mateuslll.core.port.out;

import tech.mateuslll.core.domain.User;

import java.util.Optional;

public interface UserRepositoryPortOut {

     User save (User user);

    Optional<User> findByEmail(String email);
}
