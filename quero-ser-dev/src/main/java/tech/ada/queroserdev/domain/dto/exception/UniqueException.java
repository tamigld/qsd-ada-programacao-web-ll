package tech.ada.queroserdev.domain.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UniqueException extends Exception {
    private String field;
    private String value;
}
