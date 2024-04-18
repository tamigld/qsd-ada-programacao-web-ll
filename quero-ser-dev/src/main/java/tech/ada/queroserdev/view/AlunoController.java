package tech.ada.queroserdev.view;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.queroserdev.domain.dto.exception.NotFoundException;
import tech.ada.queroserdev.domain.dto.exception.UniqueException;
import tech.ada.queroserdev.domain.dto.v1.AlunoDto;
import tech.ada.queroserdev.domain.entities.Aluno;
import tech.ada.queroserdev.domain.mappers.AlunoMapper;
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
    ) {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<AlunoDto> listarPorMatricula(
            @PathVariable("matricula") String matricula
    ) throws NotFoundException {
        if (matricula != null) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(AlunoMapper.toDto(alunoService.buscarPorMatricula(matricula)));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/turma/{turma}")
    public ResponseEntity<List<AlunoDto>> listarAlunosPorTurma(
            @PathVariable("turma") String turma
    ) throws NotFoundException {
        if (turma != null) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(alunoService.buscarPorTurma(turma));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> buscarAlunoPorId(
            @PathVariable("id") int id
    ) throws NotFoundException {
        return ResponseEntity.ok(AlunoMapper.toDto(alunoService.buscarAlunoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<AlunoDto> cadastrarAluno(
            @RequestBody @Valid AlunoDto aluno
    ) throws UniqueException {
        AlunoDto alunoDto = alunoService.criarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDto> substituirAluno(
            @PathVariable("id") int id,
            @RequestBody @Valid AlunoDto alunoDto
    ) throws NotFoundException {
        AlunoDto aluno = alunoService.substituirAluno(id, alunoDto);

        if(aluno == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(aluno);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AlunoDto> atualizarAluno(
            @PathVariable("id") int id,
            @RequestBody AlunoDto alunoDto
    ) throws UniqueException, NotFoundException {
        AlunoDto aluno = alunoService.atualizarAluno(id, alunoDto);

        if(aluno == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AlunoDto> deletarAluno(
            @PathVariable("id") int id
    ) throws NotFoundException {
        alunoService.deletarAluno(id);

        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
