package com.aaw.avaliacao.controller;

import com.aaw.avaliacao.model.Aluno;
import com.aaw.avaliacao.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Aluno> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Aluno buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        return service.criar(aluno);
    }

    @PutMapping("/{id}")
    public Aluno editar(@PathVariable Long id, @RequestBody Aluno aluno) {
        return service.editar(id, aluno);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PostMapping("/{alunoId}/curso/{cursoId}")
    public Aluno adicionarCurso(@PathVariable Long alunoId, @PathVariable Long cursoId) {
        return service.adicionarCurso(alunoId, cursoId);
    }
}


