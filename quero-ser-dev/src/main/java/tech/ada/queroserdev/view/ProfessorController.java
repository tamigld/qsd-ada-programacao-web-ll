package tech.ada.queroserdev.view;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.queroserdev.domain.dto.v1.ProfessorDto;
import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
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
    public ResponseEntity<List<ProfessorDto>> listarProfessores(){
        return ResponseEntity.ok(servico.listarProfessor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> buscarProfessorPorId(
            @PathVariable("id") int id
    ) throws NotFoundException {
        return ResponseEntity.ok(servico.buscarProfessor(id));
    }

    @PostMapping
    public ResponseEntity<ProfessorDto> criarProfessor(
            @RequestBody @Valid ProfessorDto pedido
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criarProfessor(pedido));
    }

    @PatchMapping("/{id}")
    public HttpStatus atualizarProfessor(
            @PathVariable("id") int id,
            @RequestBody ProfessorDto professorDto
    ) throws NotFoundException {
        servico.atualizarProfessor(id, professorDto);
        return HttpStatus.ACCEPTED;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDto> substituirProfessor(
            @PathVariable("id") int id,
            @RequestBody ProfessorDto pedido
    ) throws NotFoundException {
        final ProfessorDto p = servico.substituirProfessor(id, pedido);

        if(p == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(p);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletarProfessor(
            @PathVariable("id") int id
    ) throws NotFoundException {
        servico.deletarProfessor(id);
        return HttpStatus.ACCEPTED;
    }
}
