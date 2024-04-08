package tech.ada.queroserdev.service.professor;

import tech.ada.queroserdev.domain.dto.ProfessorDto;

import java.util.List;

public interface IProfessorService {

    void criarProfessor(String nome, String email);

    List<ProfessorDto> listarProfessor();

    ProfessorDto buscarProfessorPorId(int id);

    ProfessorDto atualizarProfessor(int id, String nome, String email);

}
