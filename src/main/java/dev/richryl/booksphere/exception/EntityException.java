package dev.richryl.booksphere.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class EntityException extends ResponseStatusException {

    public EntityException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public EntityException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
