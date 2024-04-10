package tech.ada.queroserdev.domain.dto.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    private int id;
    @NotBlank
    @Min(value = 3, message = "O nome deve conter mais de 2 caracteres.")
    private String nome;
    @Email(message = "E-mail com formatação errada.")
    private String email;
}
