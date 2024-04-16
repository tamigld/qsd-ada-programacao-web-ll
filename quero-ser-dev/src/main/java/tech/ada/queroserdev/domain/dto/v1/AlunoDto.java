package tech.ada.queroserdev.domain.dto.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AlunoDto {
    private int id;
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String turma;
    @CPF(message = "CPF inválido")
    private String cpf;
    @NotBlank
    private String matricula;
}
