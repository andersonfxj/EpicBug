package com.anderson.epicbug.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.anderson.epicbug.R;
import com.anderson.epicbug.adapter.GameAdapter;
import com.anderson.epicbug.model.Jogo;

import java.util.ArrayList;

public class ListajogoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private ArrayList<Jogo> listaJogo = new ArrayList<Jogo>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listajogo);

        getData();

        //Instanciando o adapter game
        gameAdapter = new GameAdapter(getApplicationContext(), listaJogo);

        //Configurando a Recycler View
        recyclerView = findViewById(R.id.recyclerViewJogo);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), manager.getOrientation());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(gameAdapter);

        //recyclerView.addItemDecoration(dividerItemDecoration);


    }


    public void getData(){
        //Preencher o lista de jogos, com dados -> ESTÀTICOS

        Jogo jogo = new Jogo("1","https://jovemnerd.com.br/wp-content/uploads/wd-ps3.jpg","FarCry",30.0);
         jogo = new Jogo("1","https://jovemnerd.com.br/wp-content/uploads/wd-ps3.jpg","FarCry",30.0);
         jogo = new Jogo("1","https://jovemnerd.com.br/wp-content/uploads/wd-ps3.jpg","FarCry",30.0);
         jogo = new Jogo("1","https://jovemnerd.com.br/wp-content/uploads/wd-ps3.jpg","FarCry",30.0);
         jogo = new Jogo("1","https://jovemnerd.com.br/wp-content/uploads/wd-ps3.jpg","FarCry",30.0);
         jogo = new Jogo("1","https://jovemnerd.com.br/wp-content/uploads/wd-ps3.jpg","FarCry",30.0);
         jogo = new Jogo("1","https://jovemnerd.com.br/wp-content/uploads/wd-ps3.jpg","FarCry",30.0);
         jogo = new Jogo("1","https://jovemnerd.com.br/wp-content/uploads/wd-ps3.jpg","FarCry",30.0);
         jogo = new Jogo("1","https://jovemnerd.com.br/wp-content/uploads/wd-ps3.jpg","FarCry",30.0);
         jogo = new Jogo("1","https://jovemnerd.com.br/wp-content/uploads/wd-ps3.jpg","FarCry",30.0);
        listaJogo.add(jogo);
        listaJogo.add(jogo);
        listaJogo.add(jogo);
        listaJogo.add(jogo);
        listaJogo.add(jogo);
        listaJogo.add(jogo);
        listaJogo.add(jogo);
        listaJogo.add(jogo);
        listaJogo.add(jogo);
        listaJogo.add(jogo);
    }

}
