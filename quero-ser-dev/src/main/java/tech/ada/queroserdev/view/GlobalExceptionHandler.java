package tech.ada.queroserdev.view;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ada.queroserdev.domain.dto.ErrorResponse;
import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
import tech.ada.queroserdev.domain.dto.exception.UniqueException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.createFromException(exception));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(final MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ErrorResponse.createFromException(ex));
    }

    @ExceptionHandler(value = UniqueException.class)
    public ResponseEntity<ErrorResponse> handleUniqueException(final UniqueException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.createFromException(exception));
    }
}
