package tech.mateuslll.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotFoundException extends DomainException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("User Not Found Exception");
        pb.setDetail("There is no user with this email. Please use another email.");

        return pb;
    }
}
