package tech.ada.queroserdev.service.aluno;

import org.springframework.stereotype.Service;
import tech.ada.queroserdev.domain.dto.exception.CustomMessageException;
import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
import tech.ada.queroserdev.domain.dto.exception.UniqueException;
import tech.ada.queroserdev.domain.dto.v1.AlunoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService implements IAlunoService{
    private final List<AlunoDto> alunos = new ArrayList<>();
    private int id = 0;
    @Override
    public AlunoDto criarAluno(AlunoDto alunoDto) throws UniqueException {
        Optional<AlunoDto> alunoC = alunos.stream().filter(a -> a.getMatricula() == alunoDto.getMatricula()).findFirst();

        if(alunoC.isPresent()){
            throw new UniqueException("matrícula", String.valueOf(alunoDto.getMatricula()));
        }

        AlunoDto aluno = new AlunoDto(id++, alunoDto.getNome(), alunoDto.getMatricula(), alunoDto.getTurma(), alunoDto.getPeriodo());
        alunos.add(aluno);

        return aluno;
    }

    @Override
    public AlunoDto buscarAlunoPorId(int id) throws NotFoundException {
        return alunos
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(AlunoDto.class, String.valueOf(id)));
    }

    @Override
    public List<AlunoDto> buscarTurma(String turma) throws CustomMessageException {
        List<AlunoDto> alunosFiltrados = new ArrayList<>();

        for(AlunoDto alunoDto : alunos){
            if(alunoDto.getTurma().equals(turma)){
                alunosFiltrados.add(alunoDto);
            }
        }

        if(alunosFiltrados.isEmpty()){
            throw new CustomMessageException("Empty expected filtered list.");
        } else{
            return alunosFiltrados;
        }
    }


    @Override
    public List<AlunoDto> listarAlunos() {
        return alunos;
    }

    @Override
    public AlunoDto substituirAluno(int id, AlunoDto alunoDto) throws NotFoundException {
        AlunoDto aluno = buscarAlunoPorId(id);
        alunos.remove(aluno);
        AlunoDto novoAluno = new AlunoDto(id, alunoDto.getNome(), alunoDto.getMatricula(), alunoDto.getTurma(), alunoDto.getPeriodo());
        alunos.add(novoAluno);

        return novoAluno;
    }

    @Override
    public AlunoDto atualizarAluno(int id, AlunoDto alunoDto) throws NotFoundException {
        final AlunoDto aluno = buscarAlunoPorId(id);

        aluno.setId(alunoDto.getId() == 0 ? aluno.getId() : 0);
        aluno.setNome(alunoDto.getNome() == null ? aluno.getNome() : alunoDto.getNome());
        aluno.setMatricula(alunoDto.getMatricula() == 0 ? aluno.getMatricula() : 0);
        aluno.setTurma(alunoDto.getTurma() == null ? aluno.getTurma() : alunoDto.getTurma());
        aluno.setPeriodo(alunoDto.getPeriodo() == null ? aluno.getPeriodo() : alunoDto.getPeriodo());

        return alunoDto;
    }
}
