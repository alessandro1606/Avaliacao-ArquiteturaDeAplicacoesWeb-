package com.aaw.avaliacao.controller;

import com.aaw.avaliacao.model.Curso;
import com.aaw.avaliacao.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Curso> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Curso buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public Curso criar(@RequestBody Curso curso) {
        return service.criar(curso);
    }

    @PutMapping("/{id}")
    public Curso editar(@PathVariable Long id, @RequestBody Curso curso) {
        return service.editar(id, curso);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
