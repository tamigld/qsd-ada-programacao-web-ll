package tech.ada.queroserdev.service.aluno;

import tech.ada.queroserdev.domain.dto.exception.CustomMessageException;
import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
import tech.ada.queroserdev.domain.dto.exception.UniqueException;
import tech.ada.queroserdev.domain.dto.v1.AlunoDto;

import java.util.List;

public interface IAlunoService {

    AlunoDto criarAluno(AlunoDto alunoDto) throws UniqueException;

    AlunoDto buscarAlunoPorId(int id) throws NotFoundException;

    List<AlunoDto> buscarTurma(String turma) throws NotFoundException, CustomMessageException;

    List<AlunoDto> listarAlunos();

    AlunoDto substituirAluno(int id, AlunoDto alunoDto) throws NotFoundException;

    AlunoDto atualizarAluno(int id, AlunoDto alunoDto) throws NotFoundException;
}
