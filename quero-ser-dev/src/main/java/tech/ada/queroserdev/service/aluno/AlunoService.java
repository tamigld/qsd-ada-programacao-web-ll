package tech.ada.queroserdev.service.aluno;

import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
import tech.ada.queroserdev.domain.dto.exception.UniqueException;
import tech.ada.queroserdev.domain.dto.v1.AlunoDto;
import tech.ada.queroserdev.domain.entities.Aluno;
import tech.ada.queroserdev.domain.mappers.AlunoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Service
public class AlunoService implements IAlunoService{
    private final List<AlunoDto> alunos = new ArrayList<>();
    private int id = 0;
    @Override
    public AlunoDto criarAluno(AlunoDto alunoDto) throws UniqueException {
        Optional<AlunoDto> alunoC = alunos
                                        .stream()
                                        .filter(a -> Objects.equals(a.getMatricula(), alunoDto.getMatricula()))
                                        .findFirst();

        if(alunoC.isPresent()){
            throw new UniqueException("matrícula", String.valueOf(alunoDto.getMatricula()));
        }

        AlunoDto aluno = new AlunoDto(id++, alunoDto.getNome(), alunoDto.getEmail(), alunoDto.getTurma(), alunoDto.getMatricula());
        alunos.add(aluno);

        return aluno;
    }

    @Override
    public Aluno buscarAlunoPorId(int id) throws NotFoundException {
        return null;
    }

    @Override
    public List<AlunoDto> buscarPorTurma(String turma) throws NotFoundException {
        return null;
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) throws NotFoundException {
        return null;
    }

    @Override
    public List<AlunoDto> listarAlunos() {
        return alunos;
    }

    @Override
    public AlunoDto substituirAluno(int id, AlunoDto alunoDto) throws NotFoundException {
        AlunoDto aluno = AlunoMapper.toDto(buscarAlunoPorId(id));
        alunos.remove(aluno);
        AlunoDto novoAluno = new AlunoDto(id, alunoDto.getNome(), alunoDto.getEmail(), alunoDto.getTurma(), alunoDto.getMatricula());
        alunos.add(novoAluno);

        return novoAluno;
    }

    @Override
    public AlunoDto atualizarAluno(int id, AlunoDto alunoDto) throws NotFoundException, UniqueException {
        return null;
    }

    @Override
    public void deletarAluno(int id) throws NotFoundException {

    }
}
