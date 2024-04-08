package tech.ada.queroserdev.service.aluno;

import tech.ada.queroserdev.domain.dto.AlunoDto;

import java.util.List;

public interface IAlunoService {

    int criarAluno(String nome, int matricula) throws Exception;

    AlunoDto buscarAlunoPorId(int id);

    List<AlunoDto> listarAlunos();

    AlunoDto atualizarAluno(String nome, int matricula);
}
