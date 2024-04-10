package tech.ada.queroserdev.service.professor;

import tech.ada.queroserdev.domain.dto.v1.ProfessorDto;
import tech.ada.queroserdev.domain.dto.exception.NotFoundException;

import java.util.List;

public interface IProfessorService {

    ProfessorDto criarProfessor(ProfessorDto professorDto);

    List<ProfessorDto> listarProfessor();

    ProfessorDto buscarProfessor(int id) throws NotFoundException;

    ProfessorDto substituirProfessor(int id, ProfessorDto professorDto) throws NotFoundException;

    ProfessorDto atualizarProfessor(int id, ProfessorDto professorDto) throws NotFoundException;

    void deletarProfessor(int id) throws NotFoundException;

}
