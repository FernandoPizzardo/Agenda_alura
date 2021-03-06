package com.pizzag.agenda2.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pizzag.agenda2.R;
import com.pizzag.agenda2.dao.AlunoDAO;
import com.pizzag.agenda2.model.Aluno;

public class FormAlunoActivity extends AppCompatActivity {

    public static final String TITLE_NOVO_ALUNO = "Novo Aluno";
    public static final String CHAVE_ALUNO = "Aluno";
    private static final String TITLE_ALUNO_EDIT = "Editar Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno alunoM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_aluno);
        setTitle(TITLE_NOVO_ALUNO);

        initCampos();
        Intent dados = getIntent();

        carregaAluno(dados);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.activity_form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_form_menu){
            finalizaForm();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno(Intent dados) {
        Intent getDados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO) || alunoM != null) {
            setTitle(TITLE_ALUNO_EDIT);

            alunoM = (Aluno) getDados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITLE_NOVO_ALUNO);
            alunoM = new Aluno();

        }
    }

    private void preencheCampos() {
        campoNome.setText(alunoM.getNome());
        campoEmail.setText(alunoM.getEmail());
        campoTelefone.setText(alunoM.getTelefone());
    }


    private void finalizaForm() {
        preencheAluno();
        if (alunoM.temIdValido()) {
            dao.edit(alunoM);
        } else {
            dao.save(alunoM);
        }
        finish();
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