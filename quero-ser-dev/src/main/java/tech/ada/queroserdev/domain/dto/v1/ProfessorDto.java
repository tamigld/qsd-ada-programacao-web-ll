package tech.ada.queroserdev.domain.dto.v1;

import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class ProfessorDto {
    @Positive
    private int id;
    @NotBlank
    private String nome;
    @CPF
    private String cpf;
    @Email
    private String email;
    @Null
    private String activity;
}
