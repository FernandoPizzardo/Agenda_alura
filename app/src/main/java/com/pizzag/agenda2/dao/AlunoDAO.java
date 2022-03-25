package com.pizzag.agenda2.dao;

import com.pizzag.agenda2.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private final static List<Aluno> alunos = new ArrayList<>();

    private static long contadorId = 1;

    public void save(Aluno aluno) {
        aluno.setId(contadorId);
        alunos.add(aluno);
        contadorId++;


    }

    public void edit(Aluno aluno) {
        Aluno alunoEncontrado = null;
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                alunoEncontrado = a;

            }
            if (alunoEncontrado != null) {
                int posicao = alunos.indexOf(alunoEncontrado);
                alunos.set(posicao, aluno);
            }
        }
    }

    public List<Aluno> findAll() {
        return new ArrayList<>(alunos);
    }
}
