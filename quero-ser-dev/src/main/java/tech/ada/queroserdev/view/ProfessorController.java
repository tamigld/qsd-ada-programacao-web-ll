package tech.ada.queroserdev.view;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.ada.queroserdev.domain.dto.ProfessorDto;
import tech.ada.queroserdev.service.professor.IProfessorService;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private final IProfessorService servico;

    public ProfessorController(IProfessorService servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<ProfessorDto> listarProfessores(){
        return servico.listarProfessor();
    }

    @GetMapping("/{id}")
    public ProfessorDto buscarProfessorPorId(
            @PathVariable("id") int id
    ){
        return servico.buscarProfessorPorId(id);
    }

    @PostMapping
    public HttpStatus criarProfessor(
            @RequestBody @Valid ProfessorDto pedido
    ){
        servico.criarProfessor(pedido.getNome(), pedido.getEmail());
        return HttpStatus.OK;
    }

    @PutMapping("/{id}")
    public ProfessorDto atualizarProfessor(
            @PathVariable("id") int id,
            @RequestBody ProfessorDto pedido
    ){
        return servico.atualizarProfessor(pedido.getId(), pedido.getNome(), pedido.getEmail());
    }
}
