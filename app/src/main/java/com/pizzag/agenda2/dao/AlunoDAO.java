package com.pizzag.agenda2.dao;

import androidx.annotation.Nullable;

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
        alunoEncontrado = findById(aluno);
        if (alunoEncontrado != null) {
            int posicao = alunos.indexOf(alunoEncontrado);
            alunos.set(posicao, aluno);
        }

    }

    @Nullable
    private Aluno findById(Aluno aluno) {
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                return a;

            }
        }
        return null;
    }

    public List<Aluno> findAll() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno alunoEscolhido) {
        Aluno aluno1 = findById(alunoEscolhido);
        if (aluno1 != null) {
            alunos.remove(aluno1);
        }
    }
}
