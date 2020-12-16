package com.anderson.epicbug.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.anderson.epicbug.MainActivity;
import com.anderson.epicbug.R;
import com.anderson.epicbug.config.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmailLogin, getEditSenhaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmailLogin = findViewById(R.id.editEmailLogin);
        getEditSenhaLogin = findViewById(R.id.editSenhaLogin);

        ConfiguracaoFirebase.getFirebaseAuth().signOut();
    }

    public void LogarUsuario(View view){
        String email = editEmailLogin.getText().toString();
        String senha = getEditSenhaLogin.getText().toString();

        ConfiguracaoFirebase.signWithEmailAndPassword(getApplicationContext(), email, senha,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){//consegui fazer o login
                            AbrirTelaPrincipal();
                        }else{//nao consegui fazer o login
                            ConfiguracaoFirebase.verifyLoginException(getApplicationContext(),task);
                        }
                    }
                });
    }

    public void AbrirTelaCadastro(View view){
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }
    public void AbrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
