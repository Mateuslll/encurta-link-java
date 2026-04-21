package tech.mateuslll.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserAlreadyExistException extends DomainException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_CONTENT);

        pb.setTitle("User Already Exists Exception");
        pb.setDetail("There is a user with this email. Please use another email.");

        return pb;
    }
}
