package tech.ada.queroserdev.service.professor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
import tech.ada.queroserdev.domain.dto.v1.ProfessorDto;
import tech.ada.queroserdev.external.FeignBoredApi;
import tech.ada.queroserdev.external.RestBoredApi;

@Service
public class ProfessorService implements IProfessorService {

    private final List<ProfessorDto> professores = new ArrayList<>();

    private final FeignBoredApi boredApi;
    private int id = 1;

    public ProfessorService(FeignBoredApi boredApi) {
        this.boredApi = boredApi;
    }


    @Override
    public ProfessorDto criarProfessor(ProfessorDto novoProfessor) {
        final ProfessorDto p = new ProfessorDto(
                id++,
                novoProfessor.getNome(),
                novoProfessor.getCpf(),
                novoProfessor.getEmail(),
                boredApi.getActivity().activity()
        );
        professores.add(p);
        return p;
    }

    @Override
    public List<ProfessorDto> listarProfessores() {
        return professores;
    }

    @Override
    public ProfessorDto buscarProfessor(int id) throws NotFoundException {
        return professores
                .stream()
                .filter(it -> it.getId()==id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(ProfessorDto.class, String.valueOf(id)));
    }

    @Override
    public ProfessorDto atualizarProfessor(int id, ProfessorDto pedido) {
        final ProfessorDto professor = professores.stream().filter(it -> it.getId() == id).findFirst().orElse(null);
        if (professor == null) {
            return null;
        }
        professores.remove(professor);
        final ProfessorDto p = new ProfessorDto(professor.getId(), pedido.getNome(), pedido.getCpf(), pedido.getEmail(), boredApi.getActivity().activity());
        professores.add(p);
        return p;
    }

    @Override
    public void removerProfessor(int id) throws NotFoundException {
        final ProfessorDto professor = buscarProfessor(id);
        professores.remove(professor);
    }

    @Override
    public ProfessorDto buscarPorCpf(String cpf) throws NotFoundException {
        return null;
    }
}
