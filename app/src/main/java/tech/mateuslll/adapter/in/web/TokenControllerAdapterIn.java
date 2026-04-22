package tech.mateuslll.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.mateuslll.adapter.in.web.dto.LoginReq;
import tech.mateuslll.adapter.in.web.dto.LoginResp;
import tech.mateuslll.core.usecase.AuthenticateUseCase;

@RestController
public class TokenControllerAdapterIn {

    private final AuthenticateUseCase authenticateUseCase;

    public TokenControllerAdapterIn(AuthenticateUseCase authenticateUseCase) {
        this.authenticateUseCase = authenticateUseCase;
    }

    @PostMapping("/oauth/token")
    public ResponseEntity<LoginResp> login(@RequestBody LoginReq loginReq) {
        var resp = authenticateUseCase.execute(loginReq);
        return ResponseEntity.ok(resp);
    }
}
