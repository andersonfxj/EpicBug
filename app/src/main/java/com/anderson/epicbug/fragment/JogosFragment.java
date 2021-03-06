package com.anderson.epicbug.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anderson.epicbug.JogosActivity;
import com.anderson.epicbug.R;
import com.anderson.epicbug.adapter.GameAdapter;
import com.anderson.epicbug.config.ConfiguracaoFirebase;
import com.anderson.epicbug.model.Jogo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class JogosFragment extends Fragment {
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private ArrayList<Jogo> listaJogo = new ArrayList<Jogo>();
    private DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //GARANTIR QUE O ITEM DE MUNU APARECERÁ PARA O FRAGMENTO
        setHasOptionsMenu(true);

        View v =  inflater.inflate(R.layout.fragment_jogos, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("jogos");


        //Instanciando o adapter game
        gameAdapter = new GameAdapter(getActivity().getApplicationContext(), listaJogo);
        getData();
        //Configurando a Recycler View
        recyclerView = v.findViewById(R.id.recyclerJogo);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), manager.getOrientation());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(gameAdapter);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab_Add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), JogosActivity.class);
                startActivity(intent);
            }
        });
        //recyclerView.addItemDecoration(dividerItemDecoration);

        gameAdapter.setOnRatingBarChangeListener(new GameAdapter.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(int position, float estrelas) {

                Toast.makeText(getActivity().getApplicationContext(),"POSICAO: "+ position+ " ESTRELAS: "+estrelas,Toast.LENGTH_SHORT).show();

                //ALTERAR OS DADOS NO FIREBASE
                //selecionando o jogo
                Jogo jogo = listaJogo.get(position);
                //setando a qtd estrelas
                jogo.setEstrelas(estrelas);
                //carregar o jogo no firebase
                databaseReference.child(jogo.getId()).setValue(jogo);

            }
        });

        return v;
    }
    //Parte Guilherme
    //Parte Guilherme
    //Parte Guilherme


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_jogos, menu);
        int n = menu.size();
        Log.i("menu",n+"");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_jogos){
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }else if(item.getItemId() == R.id.sair){
            ConfiguracaoFirebase.getFirebaseAuth().signOut();
            getActivity().finish();
        }
        return true;
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
                Toast.makeText(getActivity().getApplicationContext(),"CARREGOU DADOS!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(),"Erro ao carregar lista",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
