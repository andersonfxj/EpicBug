package com.anderson.epicbug.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.anderson.epicbug.JogosActivity;
import com.anderson.epicbug.R;
import com.anderson.epicbug.adapter.GameAdapter;
import com.anderson.epicbug.model.Jogo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListajogoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private ArrayList<Jogo> listaJogo = new ArrayList<Jogo>();
    private DatabaseReference databaseReference;
    private GameAdapter.OnRatingBarChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listajogo);
        databaseReference = FirebaseDatabase.getInstance().getReference("jogos");


        //Instanciando o adapter game
        gameAdapter = new GameAdapter(getApplicationContext(), listaJogo);
        getData();
        //Configurando a Recycler View
        recyclerView = findViewById(R.id.recyclerJogo);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), manager.getOrientation());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(gameAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_Add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JogosActivity.class);
                startActivity(intent);
            }
        });
        //recyclerView.addItemDecoration(dividerItemDecoration);

        gameAdapter.setOnRatingBarChangeListener(new GameAdapter.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(int position, float estrelas) {


               Toast.makeText(getApplicationContext(),"POSICAO: "+ position+ " ESTRELAS: "+estrelas,Toast.LENGTH_SHORT).show();

                //ALTERAR OS DADOS NO FIREBASE


            }
        });

    }


    public void getData(){
        //Preencher o lista de jogos, com dados -> ESTÀTICOS

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaJogo.clear();
                for (DataSnapshot no_children : snapshot.getChildren()) {
                    Jogo jogo = no_children.getValue(Jogo.class);
                    listaJogo.add(jogo);
                }
                //avisando o adapter que os dados mudaram
                   gameAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"CARREGOU DADOS!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Erro ao carregar lista",Toast.LENGTH_SHORT).show();

            }
        });
    }


}
