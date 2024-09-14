package me.vitorcsouza.jornada_milhas_api.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class Exceptions {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> Error(Exception exception) {
        ResponseError responseError = new ResponseError(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(responseError);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> elementNotFound() {
        return ResponseEntity.notFound().build();
    }
}
