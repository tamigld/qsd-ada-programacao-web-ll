package tech.ada.queroserdev.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
import tech.ada.queroserdev.domain.dto.exception.UniqueException;
import tech.ada.queroserdev.domain.dto.v1.AlunoDto;
import tech.ada.queroserdev.service.aluno.IAlunoService;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private final IAlunoService alunoService;
    public AlunoController(IAlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<AlunoDto>> listarAlunos(
            @RequestParam(required = false) String turma
    ) throws NotFoundException {
        if(turma == null){
            return ResponseEntity.ok(alunoService.listarAlunos());
        } else{
            return ResponseEntity.status(HttpStatus.FOUND).body(alunoService.buscarTurma(turma));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> buscarAlunoPorId(
            @PathVariable("id") int id
    ) throws NotFoundException {
        return ResponseEntity.ok(alunoService.buscarAlunoPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlunoDto> cadastrarAluno(
            @RequestBody @Valid AlunoDto aluno
    ) throws UniqueException {
        if(aluno.getNome().length() < 3){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        AlunoDto alunoDto = alunoService.criarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoDto);
    }

    @PatchMapping("/{id}")
    public AlunoDto atualizarProfessor(
            @PathVariable("id") int id,
            @RequestBody AlunoDto alunoDto
    ) throws NotFoundException {
        return alunoService.atualizarAluno(id, alunoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDto> substituirAluno(
            @PathVariable("id") int id,
            @RequestBody AlunoDto aluno
    ) throws NotFoundException {
        AlunoDto alunoEncontrado = alunoService.buscarAlunoPorId(id);

        if(alunoEncontrado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        alunoService.substituirAluno(id, aluno);
        return ResponseEntity.status(HttpStatus.OK).body(aluno);
    }
}
