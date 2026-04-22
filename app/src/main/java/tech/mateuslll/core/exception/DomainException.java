package tech.mateuslll.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

// uma boa tratativa vamos lá RFC problem details APIS PESQUISAR LEGAL
// https://www.rfc-editor.org/rfc/rfc9501.html
// Essa tratativa serve como uma padronização de erros e retornos da API
public class DomainException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("Link Shortener - Internal Server Error");

        return pb;
    }
}
