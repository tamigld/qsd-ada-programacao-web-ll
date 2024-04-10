package tech.ada.queroserdev.domain.dto.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunoDto {
    private int id;
    @NotBlank
    private String nome;
    @NotNull
    private int matricula;
    @NotBlank
    private String turma;
    @NotBlank
    private String periodo;
}
