package tech.ada.queroserdev.service.professor;

import org.springframework.stereotype.Service;
import tech.ada.queroserdev.domain.dto.v1.ProfessorDto;
import tech.ada.queroserdev.domain.dto.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService implements IProfessorService {
    private final List<ProfessorDto> professores = new ArrayList<>();
    private int id = 0;

    @Override
    public ProfessorDto criarProfessor(ProfessorDto professorDto) {
        final ProfessorDto p = new ProfessorDto(
                id++,
                professorDto.getNome(),
                professorDto.getEmail()
        );

        professores.add(p);
        return p;
    }

    @Override
    public List<ProfessorDto> listarProfessor() {
        return professores;
    }

    @Override
    public ProfessorDto buscarProfessor(int id) throws NotFoundException {
        return professores
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(ProfessorDto.class, String.valueOf(id)));
    }

    @Override
    public ProfessorDto substituirProfessor(int id, ProfessorDto professorDto) throws NotFoundException {
        final ProfessorDto professor = buscarProfessor(id);

        professores.remove(professor);
        ProfessorDto novoProfessor = new ProfessorDto(professor.getId(), professorDto.getNome(), professorDto.getEmail());
        professores.add(novoProfessor);

        return novoProfessor;
    }

    @Override
    public ProfessorDto atualizarProfessor(int id, ProfessorDto professorDto) throws NotFoundException {
        final ProfessorDto professor = buscarProfessor(id);
        professor.setId(professorDto.getId() != 0 ? professor.getId() : 0);
        professor.setNome(professorDto.getNome() == null ? professor.getNome() : professorDto.getNome());
        professor.setEmail(professorDto.getEmail() == null ? professor.getEmail() : professorDto.getEmail());

        return professor;
    }

    @Override
    public void deletarProfessor(int id) throws NotFoundException {
        final ProfessorDto professor = buscarProfessor(id);
        professores.remove(professor);
    }
}
