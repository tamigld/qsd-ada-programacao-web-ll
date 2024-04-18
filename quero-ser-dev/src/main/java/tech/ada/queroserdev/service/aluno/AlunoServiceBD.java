package tech.ada.queroserdev.service.aluno;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
import tech.ada.queroserdev.domain.dto.exception.UniqueException;
import tech.ada.queroserdev.domain.dto.v1.AlunoDto;
import tech.ada.queroserdev.domain.entities.Aluno;
import tech.ada.queroserdev.domain.mappers.AlunoMapper;
import tech.ada.queroserdev.domain.mappers.ProfessorMapper;
import tech.ada.queroserdev.external.RestBoredApi;
import tech.ada.queroserdev.repositories.AlunoRepository;
import tech.ada.queroserdev.utils.MatriculaUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Primary
public class AlunoServiceBD implements IAlunoService{
    private final AlunoRepository repository;
    private RestBoredApi restBoredApi;

    public AlunoServiceBD(AlunoRepository alunoRepository, RestBoredApi restBoredApi) {
        this.repository = alunoRepository;
        this.restBoredApi = restBoredApi;
    }

    @Override
    public AlunoDto criarAluno(AlunoDto alunoDto) throws UniqueException {
        int matricula = MatriculaUtils.gerarMatriculaUnica();

        if(repository.findByMatricula(String.valueOf(alunoDto.getMatricula())).isPresent()){
            throw new UniqueException("matricula", String.valueOf(alunoDto.getMatricula()));
        }

        Aluno a = AlunoMapper.toEntity(alunoDto, String.valueOf(matricula));
        return AlunoMapper.toDto(repository.save(a));

    }

    @Override
    public Aluno buscarAlunoPorId(int id) throws NotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(Aluno.class, String.valueOf(id)));
    }

    @Override
    public List<AlunoDto> buscarPorTurma(String turma) throws NotFoundException {
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
    public Aluno buscarPorMatricula(String matricula) throws NotFoundException {
        return repository
                .findByMatricula(matricula)
                .orElseThrow(() -> new NotFoundException(AlunoDto.class, "matricula " + matricula));
    }

    @Override
    public List<AlunoDto> listarAlunos() {
        return repository.findAll().stream().map(AlunoMapper::toDto).toList();
    }

    @Override
    public AlunoDto substituirAluno(int id, AlunoDto alunoDto) throws NotFoundException {
        Aluno a = buscarAlunoPorId(id);

        a.setId(id);
        a.setNome(alunoDto.getNome());
        a.setTurma(alunoDto.getTurma());
        a.setEmail(alunoDto.getEmail());
        a.setMatricula(alunoDto.getMatricula() == null ? a.getMatricula() : String.valueOf(MatriculaUtils.gerarMatriculaUnica()));

        return AlunoMapper.toDto(repository.save(a));
    }

    @Override
    public AlunoDto atualizarAluno(int id, AlunoDto alunoDto) throws NotFoundException, UniqueException {
        Aluno a = buscarAlunoPorId(id);

        if(repository.findByMatricula(alunoDto.getMatricula()).isPresent()){
            throw new UniqueException("matricula", String.valueOf(id));
        }

        a.setNome(alunoDto.getNome() == null ? a.getNome() : alunoDto.getNome());
        a.setTurma(alunoDto.getTurma() == null ? a.getTurma() : alunoDto.getTurma());
        a.setEmail(alunoDto.getEmail() == null ? a.getEmail() : alunoDto.getEmail());
        a.setMatricula(alunoDto.getMatricula() == null ? a.getMatricula() : String.valueOf(MatriculaUtils.gerarMatriculaUnica()));

        return AlunoMapper.toDto(repository.save(a));
    }

    @Override
    public void deletarAluno(int id) throws NotFoundException {
        if(repository.findById(id).isEmpty()){
            throw new NotFoundException(AlunoDto.class, String.valueOf(id));
        }

        repository.deleteById(id);
    }
}
