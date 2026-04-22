package tech.mateuslll.adapter.out.security;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import tech.mateuslll.core.domain.User;
import tech.mateuslll.core.port.out.TokenGeneratorPortOut;

import java.time.Instant;

import static tech.mateuslll.config.Constants.TOKEN_EXPIRATION;

@Component
public class JwtTokenAdapterOut implements TokenGeneratorPortOut {

    private final JwtEncoder jwtEncoder;

    public JwtTokenAdapterOut(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public TokenDetails generate(User user) {
        var now = Instant.now();
        var expiresIn = TOKEN_EXPIRATION;

        var claims = JwtClaimsSet.builder()
                .issuer("link-shortener")
                .subject(user.getUserId().toString())
                .claim("email", user.getEmail())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var tokenValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new TokenDetails(tokenValue, expiresIn);
    }
}
