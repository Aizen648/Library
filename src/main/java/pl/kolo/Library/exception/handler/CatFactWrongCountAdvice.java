package pl.kolo.Library.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kolo.Library.exception.exception.CatFactWrongCountException;

@ControllerAdvice
public class CatFactWrongCountAdvice {
    @ExceptionHandler(CatFactWrongCountException.class)
    public ResponseEntity<String> handlerCatFactWrongCount(CatFactWrongCountException catFactWrongCountException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error occurred: "+ catFactWrongCountException.getMessage());
    }
}
