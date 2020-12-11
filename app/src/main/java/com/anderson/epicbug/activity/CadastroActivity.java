package com.anderson.epicbug.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class CadastroActivity extends AppCompatActivity {

    private EditText editnomecadastro, editemailcadastro, editsenhacadastro,
            editconfirmsenha;
    //private CheckBox checkBoxTermo;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        //DatabaseReference reference ConfiguracaoFirebase.getFirebaseDatabase

        editnomecadastro = findViewById(R.id.editnomecadastro);
        editemailcadastro = findViewById(R.id.editnomecadastro);
        editsenhacadastro = findViewById(R.id.editnomecadastro);
        editconfirmsenha = findViewById(R.id.editconfirmsenha);
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

    public void cadastrarUsuario(Usuario usuario) {
        auth.createUserWithEmailAndPassword(usuario.getEmailCadastro(), usuario.getSenhaCadastro())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Sucesso ao cadastrar Usuario!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
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

