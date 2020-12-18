package com.anderson.epicbug.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.anderson.epicbug.R;
import com.anderson.epicbug.config.ConfiguracaoFirebase;
import com.anderson.epicbug.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

// PARTE ANDERSON MOREIRA
// PARTE ANDERSON MOREIRA
// PARTE ANDERSON MOREIRA


public class CadastroActivity extends AppCompatActivity {

    private EditText editnomecadastro, editemailcadastro, editsenhacadastro;
    //private CheckBox checkBoxTermo;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editnomecadastro = findViewById(R.id.editnomecadastro);
        editemailcadastro = findViewById(R.id.editemailcadastro);
        editsenhacadastro = findViewById(R.id.editsenhacadastro);

        //checkBoxTermo = findViewById(R.id.checkBoxTermo);
        auth = ConfiguracaoFirebase.getFirebaseAuth();

        //checkListeners();

    }

    /*public void checkListeners() {
        checkBoxTermo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                     if(CheckBoxTermo.isChecked()){
                       strResult+="Verde selecionado!\n";
                    }
                }
            }
        });
    }*/

    public void cadastrarUsuario(final Usuario usuario) {
        Log.d("USUARIO",usuario.toString());
        auth.createUserWithEmailAndPassword(usuario.getEmailCadastro(), usuario.getSenhaCadastro())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Sucesso ao cadastrar Usuario!", Toast.LENGTH_SHORT).show();
                            criarUsuarioDatabase(usuario);
                            finish();
                        }else{
                            ConfiguracaoFirebase.verifyLoginException(getApplicationContext(),task);
                        }
                    }
                });
    }
    public void criarUsuarioDatabase(Usuario usuario){
        DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabase().child("usuarios");

        //criar um nó dentro de usuarios
        DatabaseReference refupload = databaseReference.push();
        usuario.setId(refupload.getKey());
        //Salvando no dataBase
        refupload.setValue(usuario);

    }


    public void validarUsuario(View v) {
        String nome = editnomecadastro.getText().toString();
        String email = editemailcadastro.getText().toString();
        String senha = editsenhacadastro.getText().toString();

        if (!nome.isEmpty()) {//verificando o nome
            if (!email.isEmpty()) {//verificando email
                if (!senha.isEmpty()) {

                    Usuario usuario = new Usuario(nome, email, senha);
                    cadastrarUsuario(usuario);

                } else {
                    Toast.makeText(getApplicationContext(), "Preencha a senha!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Preencha a email!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Preencha o nome!", Toast.LENGTH_SHORT).show();
        }
    }
}


