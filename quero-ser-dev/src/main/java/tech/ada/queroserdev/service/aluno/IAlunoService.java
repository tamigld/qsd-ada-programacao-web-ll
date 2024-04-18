package tech.ada.queroserdev.service.aluno;

import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
import tech.ada.queroserdev.domain.dto.exception.UniqueException;
import tech.ada.queroserdev.domain.dto.v1.AlunoDto;
import tech.ada.queroserdev.domain.entities.Aluno;

import java.util.List;

public interface IAlunoService {

    AlunoDto criarAluno(AlunoDto alunoDto) throws UniqueException;

    Aluno buscarAlunoPorId(int id) throws NotFoundException;

    List<AlunoDto> buscarPorTurma(String turma) throws NotFoundException;

    Aluno buscarPorMatricula(String matricula) throws NotFoundException;

    List<AlunoDto> listarAlunos();

    AlunoDto substituirAluno(int id, AlunoDto alunoDto) throws NotFoundException;

    AlunoDto atualizarAluno(int id, AlunoDto alunoDto) throws NotFoundException, UniqueException;

    void deletarAluno(int id) throws NotFoundException;
}
