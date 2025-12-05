package com.aaw.avaliacao.service;

import com.aaw.avaliacao.model.Aluno;
import com.aaw.avaliacao.model.Curso;
import com.aaw.avaliacao.repository.AlunoRepository;
import com.aaw.avaliacao.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repo;
    private final CursoRepository cursoRepo;

    public AlunoService(AlunoRepository repo, CursoRepository cursoRepo) {
        this.repo = repo;
        this.cursoRepo = cursoRepo;
    }

    public List<Aluno> listar() {
        return repo.findAll();
    }

    public Aluno buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public Aluno criar(Aluno aluno) {
        return repo.save(aluno);
    }

    public Aluno editar(Long id, Aluno novo) {
        Aluno aluno = buscar(id);
        aluno.setNome(novo.getNome());
        aluno.setEmail(novo.getEmail());
        aluno.setMatricula(novo.getMatricula());
        return repo.save(aluno);
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }

    public Aluno adicionarCurso(Long alunoId, Long cursoId) {
        Aluno aluno = buscar(alunoId);
        Curso curso = cursoRepo.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        aluno.getCursos().add(curso);
        return repo.save(aluno);
    }
}

