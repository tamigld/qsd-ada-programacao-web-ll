package tech.ada.queroserdev.service.professor;

import org.springframework.stereotype.Service;
import tech.ada.queroserdev.domain.dto.ProfessorDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService implements IProfessorService {
    private final List<ProfessorDto> professores = new ArrayList<>();
    private int id = 0;

    @Override
    public void criarProfessor(String nome, String email) {
        professores.add(new ProfessorDto(id, nome, email));
        id++;
    }

    @Override
    public List<ProfessorDto> listarProfessor() {
        return professores;
    }

    @Override
    public ProfessorDto buscarProfessorPorId(int id) {
        Optional<ProfessorDto> professor = professores
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst();
        return professor.orElse(null);
    }

    @Override
    public ProfessorDto atualizarProfessor(int id, String nome, String email) {
        Optional<ProfessorDto> professor = professores
                .stream()
                .filter(it -> it.getId() == id)
                .findFirst();
        if (professor.isPresent()) {
            professores.remove(professor.get());
            professores.add(new ProfessorDto(id, nome, email));
        }
        return null;
    }
}
