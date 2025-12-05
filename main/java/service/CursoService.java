package com.aaw.avaliacao.service;

import com.aaw.avaliacao.model.Curso;
import com.aaw.avaliacao.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository repo;

    public CursoService(CursoRepository repo) {
        this.repo = repo;
    }

    public List<Curso> listar() {
        return repo.findAll();
    }

    public Curso buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public Curso criar(Curso curso) {
        return repo.save(curso);
    }

    public Curso editar(Long id, Curso novo) {
        Curso curso = buscar(id);
        curso.setNome(novo.getNome());
        curso.setCargaHoraria(novo.getCargaHoraria());
        return repo.save(curso);
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }
}


