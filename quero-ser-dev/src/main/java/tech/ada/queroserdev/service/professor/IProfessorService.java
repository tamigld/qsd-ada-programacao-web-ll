package tech.ada.queroserdev.service.professor;

import java.util.List;

import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
import tech.ada.queroserdev.domain.dto.v1.ProfessorDto;

public interface IProfessorService {

    ProfessorDto criarProfessor(ProfessorDto pedido);

    List<ProfessorDto> listarProfessores();

    ProfessorDto buscarProfessor(int id) throws NotFoundException;

    ProfessorDto atualizarProfessor(int id, ProfessorDto pedido) throws NotFoundException;

    void removerProfessor(int id) throws NotFoundException;

    ProfessorDto buscarPorCpf(String cpf) throws NotFoundException;

}
