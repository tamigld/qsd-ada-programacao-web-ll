package tech.ada.queroserdev.domain.mappers;

import org.springframework.web.bind.MethodArgumentNotValidException;
import tech.ada.queroserdev.domain.dto.v1.AlunoDto;
import tech.ada.queroserdev.domain.entities.Aluno;

public class AlunoMapper {
    public static Aluno toEntity(AlunoDto alunoDto, String matricula){
        return Aluno
                .builder()
                .nome(alunoDto.getNome())
                .email(alunoDto.getEmail())
                .turma(alunoDto.getTurma())
                .matricula(matricula)
                .build();
    }
    public static AlunoDto toDto(Aluno aluno){
        return new AlunoDto(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getTurma(), aluno.getMatricula());
    }
}
