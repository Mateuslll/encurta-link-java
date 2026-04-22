package tech.mateuslll.core.usecase;

import org.springframework.stereotype.Component;
import tech.mateuslll.adapter.in.web.dto.LoginReq;
import tech.mateuslll.adapter.in.web.dto.LoginResp;
import tech.mateuslll.core.exception.LoginException;
import tech.mateuslll.core.exception.UserNotFoundException;
import tech.mateuslll.core.port.in.AuthenticatePortIn;
import tech.mateuslll.core.port.out.PasswordEncoderPortOut;
import tech.mateuslll.core.port.out.TokenGeneratorPortOut;
import tech.mateuslll.core.port.out.UserRepositoryPortOut;

@Component
public class AuthenticateUseCase implements AuthenticatePortIn {

    private final PasswordEncoderPortOut passwordEncoderPortOut;
    private final UserRepositoryPortOut userRepositoryPortOut;
    private final TokenGeneratorPortOut tokenGeneratorPortOut;

    public AuthenticateUseCase(PasswordEncoderPortOut passwordEncoderPortOut,
                               UserRepositoryPortOut userRepositoryPortOut, TokenGeneratorPortOut tokenGeneratorPortOut) {
        this.passwordEncoderPortOut = passwordEncoderPortOut;
        this.tokenGeneratorPortOut = tokenGeneratorPortOut;
        this.userRepositoryPortOut = userRepositoryPortOut;
    }

    @Override
    public LoginResp execute(LoginReq loginReq) {

        var user = this.userRepositoryPortOut.findByEmail(loginReq.email()).orElseThrow(
                UserNotFoundException::new);


        if (!passwordEncoderPortOut.matches(loginReq.password(), user.getPassword())) {
            throw new LoginException();
        }

        var tokenDetails = tokenGeneratorPortOut.generate(user);

        return new LoginResp(tokenDetails.token(), tokenDetails.expiresIn());
        //Estamos utilizando o oauth2-resource-server,
        // ele consegue validar o token apartir da public key validar o token
        // Existe o autorization server e o resource server


        // comparar senha
        // se a senha for valida gera o token
        // se a senha nao for validada
        // retorna erro de credenciais invalidas

    }
}
