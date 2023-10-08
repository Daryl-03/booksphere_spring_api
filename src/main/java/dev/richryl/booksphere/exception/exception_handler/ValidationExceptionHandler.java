package dev.richryl.booksphere.exception.exception_handler;

import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        List<String> fieldErrors = new ArrayList<>();
        result.getFieldErrors().forEach(
                fieldError -> fieldErrors.add(
                        fieldError.getObjectName()
                                + "." +
                                fieldError.getField()
                                + " : " +
                                fieldError.getDefaultMessage()
                                + " : rejected value [" +
                                fieldError.getRejectedValue() +
                                "]"
                )
        );

        result.getGlobalErrors().forEach(
                objectError -> fieldErrors.add(
                        objectError.getObjectName()
                                + ": "
                                + objectError.getDefaultMessage()
                )
        );

        return new Error(HttpStatus.BAD_REQUEST, fieldErrors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(
            ConstraintViolationException exception) {
        StringBuilder errorMessage = new StringBuilder();
        exception.getConstraintViolations().forEach(
                constraintViolation -> errorMessage.append(
                constraintViolation.getPropertyPath().toString().split("\\.")[1])
                .append(" : ")
                .append(constraintViolation.getMessage())
                .append(" : rejected value [")
                .append(constraintViolation.getInvalidValue())
                .append("]")
                .append("\n"));
        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
    }

    @Getter
    @Setter
    public static class Error {
        private String timestamp;
        private int status;
        private String error;
        //        private String errorMessage;
        private List<String> fieldErrors;

        public Error(HttpStatus status, List<String> fieldErrors) {
            timestamp = String.valueOf(LocalDateTime.now());
            this.status = status.value();
            this.error = status.name();
//            this.errorMessage = error;
            this.fieldErrors = fieldErrors;
        }
    }
}
