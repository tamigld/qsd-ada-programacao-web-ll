package tech.ada.queroserdev.view;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.ada.queroserdev.domain.dto.AlunoDto;
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
    public List<AlunoDto> listarAlunos() {
        return alunoService.listarAlunos();
    }

    @GetMapping("/{id}")
    public AlunoDto buscarAlunoPorId(
            @PathVariable int id
    ){
        return alunoService.buscarAlunoPorId(id);
    }

    @PostMapping
    public HttpStatus cadastrarAluno(
            @RequestBody @Valid AlunoDto aluno
    ) throws Exception {
        try{
            alunoService.criarAluno(aluno.getNome(), aluno.getMatricula());
            return HttpStatus.OK;
        }catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PutMapping("/{id}")
    public HttpStatus atualizarAluno(
            @PathVariable("id") int id,
            @RequestBody AlunoDto aluno
    ) throws Exception {
        try{
            alunoService.atualizarAluno(aluno.getNome(), aluno.getMatricula());
            return HttpStatus.ACCEPTED;
        }catch(Exception e){
            return HttpStatus.BAD_REQUEST;
        }
    }
}
