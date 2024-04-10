package tech.ada.queroserdev.domain.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomMessageException extends Exception{
    private final String message;
}
