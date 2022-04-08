package com.pizzag.agenda2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
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
    public static final String CHAVE_ALUNO = "Aluno";
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITLE);
        configuraLista();


        fabADDsettings();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_aluno_menu, menu);
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

        atualizaAluno();
    }

    private void atualizaAluno() {
        adapter.clear();
        adapter.addAll(dao.findAll());
    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_main_lista_de_alunos);
        configuraAdapter(listaAlunos);
        configuraCliqueListenerporItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void configuraCliqueListenerporItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                abreFormEditAluno(alunoEscolhido);
            }

            private void abreFormEditAluno(Aluno alunoEscolhido) {
                Intent goToForm = new Intent(ListaAlunoActivity.this, FormAlunoActivity.class);
                goToForm.putExtra(CHAVE_ALUNO, alunoEscolhido);
                startActivity(goToForm);
            }
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final int itemId = item.getItemId();
        CharSequence tituloDoMenu = item.getTitle();
        if (itemId == R.id.activity_lista_alunos_menu) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno aluno = adapter.getItem(menuInfo.position);
            dao.remove(aluno);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraAdapter(ListView listaAlunos) {
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        listaAlunos.setAdapter(adapter);
    }
}
