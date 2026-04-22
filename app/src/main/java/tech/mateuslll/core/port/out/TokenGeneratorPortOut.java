package tech.mateuslll.core.port.out;

import tech.mateuslll.core.domain.User;

public interface TokenGeneratorPortOut {
    // Pode retornar uma string ou um objeto contendo o token e o tempo de expiração
    TokenDetails generate(User user);

    // Objeto simples para trafegar os dados do token sem amarrar ao LoginResp do controller
    record TokenDetails(String token, long expiresIn) {}
}
