package com.pizzag.agenda2.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pizzag.agenda2.R;
import com.pizzag.agenda2.dao.AlunoDAO;
import com.pizzag.agenda2.model.Aluno;

public class FormAlunoActivity extends AppCompatActivity {

    public static final String NOVO_ALUNO = "Novo Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();
    private  Aluno alunoM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_aluno);
        setTitle(NOVO_ALUNO);

        initCampos();
        SaveBotao();
        Intent dados = getIntent();

        if (dados.hasExtra("Aluno") || alunoM != null) {
            Intent getDados = getIntent();
            alunoM = (Aluno) getDados.getSerializableExtra("Aluno");
            campoNome.setText(alunoM.getNome());
            campoEmail.setText(alunoM.getEmail());
            campoTelefone.setText(alunoM.getTelefone());
        }
        else{

        }

    }

    private void SaveBotao() {
        Button botaoSalvar = findViewById(R.id.activity_button_save);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Aluno aluno = criaAluno(campoNome, campoTelefone, campoEmail);
//
//
//                dao.save(aluno);
//
//                finish();
                preencheAluno();
                dao.edit(alunoM);


            }
        });
    }

    private void initCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        alunoM.setNome(nome);
        alunoM.setEmail(email);
        alunoM.setTelefone(telefone);


    }

}