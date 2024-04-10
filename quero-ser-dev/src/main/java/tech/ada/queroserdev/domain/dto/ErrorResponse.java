package tech.ada.queroserdev.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.bind.MethodArgumentNotValidException;
import tech.ada.queroserdev.domain.dto.exception.CustomMessageException;
import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
import tech.ada.queroserdev.domain.dto.exception.UniqueException;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class ErrorResponse {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Collection<ErrorMessage> errors;

    private ErrorResponse(String message){
        this.message = message;
    }

    private ErrorResponse(String message, Collection<ErrorMessage> errors){
        this(message);
        this.errors = errors;
    }

    public static ErrorResponse createFromException(NotFoundException ex){
        String message = "No record of " + ex.getClazz().getSimpleName() + " found for id " + ex.getId();
        return new ErrorResponse(message);
    }

    public static ErrorResponse createFromException(MethodArgumentNotValidException ex){
        var violations = ex
                .getFieldErrors()
                .stream()
                .map(cv -> new ErrorMessage(cv.getField(), cv.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ErrorResponse("Validation errors: ", violations);
    }

    public static ErrorResponse createFromException(UniqueException ue){
        String message = "Field " + ue.getField() + " with value " + ue.getValue() + " already exists. (must be unique)";
        return new ErrorResponse(message);
    }

    public static ErrorResponse createMessageFromException(CustomMessageException cme){
        String message = cme.getMessage();
        return new ErrorResponse(message);
    }
}
