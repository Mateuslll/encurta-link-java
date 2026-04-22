package tech.mateuslll.core.port.in;

import tech.mateuslll.adapter.in.web.dto.LoginReq;
import tech.mateuslll.adapter.in.web.dto.LoginResp;

public interface AuthenticatePortIn {

    LoginResp execute(LoginReq loginReq);
}
