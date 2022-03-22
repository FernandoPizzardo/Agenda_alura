package com.pizzag.agenda2.dao;

import com.pizzag.agenda2.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private final static List<Aluno> alunos = new ArrayList<>();

    public void save(Aluno aluno) {
        alunos.add(aluno);


    }

    public List<Aluno> findAll() {
        return new ArrayList<>(alunos);
    }
}
