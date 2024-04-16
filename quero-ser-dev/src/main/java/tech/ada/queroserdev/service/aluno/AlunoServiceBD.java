package tech.ada.queroserdev.service.aluno;

import jakarta.validation.Valid;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
import tech.ada.queroserdev.domain.dto.v1.AlunoDto;
import tech.ada.queroserdev.domain.entities.Aluno;
import tech.ada.queroserdev.domain.mappers.AlunoMapper;
import tech.ada.queroserdev.repositories.AlunoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class AlunoServiceBD implements IAlunoService{
    private final AlunoRepository repository;

    public AlunoServiceBD(AlunoRepository alunoRepository) {
        this.repository = alunoRepository;
    }

    @Override
    public AlunoDto criarAluno(AlunoDto alunoDto){

        Aluno a = AlunoMapper.toEntity(alunoDto);
        return AlunoMapper.toDto(repository.save(a));

    }

    @Override
    public AlunoDto buscarAlunoPorId(int id) throws NotFoundException {
        return AlunoMapper
                .toDto(repository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException(Aluno.class, String.valueOf(id))));
    }

    @Override
    public List<AlunoDto> buscarTurma(String turma) throws NotFoundException {
        List<Aluno> listaAlunos = repository.findAll().stream().toList();

        List<AlunoDto> alunosFiltrados = new ArrayList<>();

        for(Aluno aluno : listaAlunos){
            if(aluno.getTurma().equals(turma)){
                alunosFiltrados.add(AlunoMapper.toDto(aluno));
            }
        }

        if(alunosFiltrados.isEmpty()){
            throw new NotFoundException(Aluno.class, ("turma " + turma));
        } else{
            return alunosFiltrados;
        }
    }

    @Override
    public List<AlunoDto> listarAlunos() {
        return repository
                .findAll()
                .stream()
                .map(AlunoMapper::toDto)
                .toList();
    }

    @Override
    public AlunoDto substituirAluno(int id, AlunoDto alunoDto) throws NotFoundException {
        return null;
    }

    @Override
    public AlunoDto atualizarAluno(int id, AlunoDto alunoDto) throws NotFoundException {
        return null;
    }
}
