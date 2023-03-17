package id.mdm17.validation.controller;

import java.util.HashMap;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import id.mdm17.validation.dto.DataResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> internalError(RuntimeException ex) {
        log.error("Internal Server Error", ex);
        return ResponseEntity.status(500).body(new DataResponse(500, "Internal Server Error", null));
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> validationRequestError(ConstraintViolationException e) {
        log.error("Validation Request Error", e);
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        HashMap<String, String> errors = new HashMap<>();

        constraintViolations.forEach(violation -> errors.put(
                violation.getPropertyPath().toString(),
                violation.getMessage()));

        return ResponseEntity.status(422).body(new DataResponse(422, "bad request", errors));
    }

    @ExceptionHandler({ HttpClientErrorException.class })
    public ResponseEntity<Object> badRequestError(HttpClientErrorException e) {
        log.error("Bad Request Error", e);
        return ResponseEntity.status(400).body(new DataResponse(400, e.getStatusText(), e.getCause()));
    }
}
