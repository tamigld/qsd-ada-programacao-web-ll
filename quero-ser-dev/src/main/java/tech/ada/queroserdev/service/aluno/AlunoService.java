package tech.ada.queroserdev.service.aluno;

import org.springframework.stereotype.Service;
import tech.ada.queroserdev.domain.dto.AlunoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService implements IAlunoService{
    private final List<AlunoDto> alunos = new ArrayList<>();
    private int id = 0;
    @Override
    public int criarAluno(String nome, int matricula) throws Exception {
        if(nome.length() < 3){
            throw new Exception();
        } else{
            alunos.add(new AlunoDto(id, nome, matricula));
            return id++;
        }
    }

    @Override
    public AlunoDto buscarAlunoPorId(int id) {
        Optional<AlunoDto> aluno = alunos
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst();
        return aluno.orElse(null);
    }

    @Override
    public List<AlunoDto> listarAlunos() {
        return alunos;
    }

    @Override
    public AlunoDto atualizarAluno(String nome, int matricula) {
        Optional<AlunoDto> aluno = alunos
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst();
        if (aluno.isPresent()) {
            alunos.remove(aluno.get());
            alunos.add(new AlunoDto(id, nome, matricula));
        }

        return null;
    }
}
