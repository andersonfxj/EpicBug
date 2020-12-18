package com.anderson.epicbug.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anderson.epicbug.R;
import com.anderson.epicbug.config.ConfiguracaoFirebase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarrinhoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarrinhoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CarrinhoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarrinhoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarrinhoFragment newInstance(String param1, String param2) {
        CarrinhoFragment fragment = new CarrinhoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //Parte Guilherme
    //Parte Guilherme
    //Parte Guilherme
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,//adsadadadasdsa
                             Bundle savedInstanceState) {
        //GARANTIR QUE O ITEM DE MUNU APARECERÁ PARA O FRAGMENTO
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jogos, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) { //adsadadadasdsa
        inflater.inflate(R.menu.menu_carrinho, menu);
        int n = menu.size();
        Log.i("menu", n + "");
    }

    public boolean onOptionsItemSelected(MenuItem item) { //adsadadadasdsa
        if (item.getItemId() == R.id.action_carrinho) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }else if(item.getItemId() == R.id.sair){
            ConfiguracaoFirebase.getFirebaseAuth().signOut();
            getActivity().finish();
        }
        return true;
    }
}
