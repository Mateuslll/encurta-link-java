package tech.mateuslll.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.mateuslll.adapter.in.web.dto.CreateUserReq;
import tech.mateuslll.adapter.in.web.dto.CreateUserResp;
import tech.mateuslll.core.port.in.CreaterUserPortIn;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
public class UserControllerAdapterIn {

    private final CreaterUserPortIn createrUserPortIn;

    public UserControllerAdapterIn(CreaterUserPortIn createrUserPortIn) {
        this.createrUserPortIn = createrUserPortIn;
    }

    @PostMapping
    public ResponseEntity<CreateUserResp> createUser(@RequestBody CreateUserReq req) {

        var userCreated = createrUserPortIn.execute(req.toDomain());
        var body = CreateUserResp.fromDomain(userCreated);

        return ResponseEntity.created(URI.create("/")).body(body);
    }
}
