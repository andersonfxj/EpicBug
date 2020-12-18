package com.anderson.epicbug.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anderson.epicbug.R;
import com.anderson.epicbug.model.Jogo;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 1º Implementar o ViewHolder
 * 2º Implementar o construtor da V.H
 * 3º Terminar de Extender o adapter
 * 4 º Implementar o construtor do adapter
 * 5º Implementar os métodos do adapter
 * */

//View Holder
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    private Context context;
    private ArrayList<Jogo> listaJogo;
    private OnRatingBarChangeListener listener;



    public void setOnRatingBarChangeListener(OnRatingBarChangeListener onRatingBarChangeListener) {
        this.listener = onRatingBarChangeListener;

    }

    public GameAdapter(Context c, ArrayList<Jogo> l){
        context = c;
        listaJogo = l;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /* Convertendo o XML -> View */
        View v = LayoutInflater.from(context).inflate(R.layout.item_game,parent, false);
        return new GameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Jogo jogo = listaJogo.get(position);
        holder.nomeJogo.setText("Nome: "+jogo.getNomeJogo());
        holder.precoJogo.setText("R$ "+jogo.getPrecoJogo().toString());
        Glide.with(context).load(jogo.getUriImagem()).into(holder.uriImagem);
        if(jogo.getEstrelas()!=null){
            holder.ratingBar.setRating(jogo.getEstrelas());
        }
       }

    @Override
    public int getItemCount() {
        return listaJogo.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder{
        //atributos
        CircleImageView uriImagem ;
        TextView nomeJogo;
        TextView precoJogo;
        RatingBar ratingBar;
        //implementando a V.h
        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            uriImagem = itemView.findViewById(R.id.imagemUri);
            nomeJogo = itemView.findViewById(R.id.nomeJogo);
            precoJogo = itemView.findViewById(R.id.precoJogo);
            ratingBar = itemView.findViewById(R.id.estrelas);
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        int position = getAdapterPosition();
                        if(b==true){
                            listener.onRatingChanged(position,v);
                        }
                    }
            });

        }


    }

    public interface OnRatingBarChangeListener{
        void onRatingChanged(int position, float estrelas);
    }
}
