package com.pizzag.agenda2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pizzag.agenda2.R;
import com.pizzag.agenda2.dao.AlunoDAO;
import com.pizzag.agenda2.model.Aluno;

import java.util.List;

public class ListaAlunoActivity extends AppCompatActivity {

    public static final String TITLE = "Lista de Alunos";
    private static final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITLE);

        fabADDsettings();

    }

    private void fabADDsettings() {
        FloatingActionButton fabAdd = findViewById(R.id.activity_main_fab);
        fabAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                abreFormularioAlunoActivity();
            }
        });
    }

    private void abreFormularioAlunoActivity() {
        startActivity(new Intent(this, FormAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();


        configuraLista();

    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_main_lista_de_alunos);
        final List<Aluno> alunos = dao.findAll();
        listaAlunos.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                alunos));
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = alunos.get(posicao);
                Intent goToForm = new Intent(ListaAlunoActivity.this, FormAlunoActivity.class);
                goToForm.putExtra("Aluno", alunoEscolhido);
                startActivity(goToForm);

            }
        });
    }
}
