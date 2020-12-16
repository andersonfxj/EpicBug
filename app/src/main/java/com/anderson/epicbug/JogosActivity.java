package com.anderson.epicbug;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.net.Uri;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.anderson.epicbug.model.Jogo;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.UploadTask;

import java.text.Format;

public class JogosActivity extends AppCompatActivity {
    private EditText editCadastroNome;
    private EditText editCadastroPreco;
    private ImageView imageView;
    private Button btnSelecionar, btnUpload;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private Uri imageUri;
    public static final int PICK_IMAGE_GALLERY=1;
    private FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogos);
        imageView = findViewById(R.id.imageCadastro);
        editCadastroNome = findViewById(R.id.editCadastroNome);
        editCadastroPreco = findViewById(R.id.editCadastroPreco);
        btnSelecionar = findViewById(R.id.btnSelecionar);
        btnUpload =findViewById(R.id.btnCadastrar);
        if(auth.getCurrentUser()==null){
            //Fazer o login com usuario admin
            auth.signInWithEmailAndPassword("cardoso@gmail.com","9664578943")
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),
                                        "Logado!",
                                        Toast.LENGTH_LONG
                                ).show();
                            }else{
                                Toast.makeText(getApplicationContext(),
                                        "Não consegui fazer o login!",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }
                    });
        }



        storageReference = FirebaseStorage.getInstance().getReference("jogos");
        databaseReference = FirebaseDatabase.getInstance().getReference("jogos");

        btnSelecionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirImagem();
            }

        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnUpload.setClickable(false);
                uploadImagem();
            }
        });
    }
    public void uploadImagem(){
        if(imageUri==null) {
            Toast.makeText(this, "Selecione uma imagem!", Toast.LENGTH_LONG).show();
            btnUpload.setClickable(true);
            return;
        }
        StorageReference imagemRef = storageReference
                .child(System.currentTimeMillis()+"."+getFileExtension(imageUri));

        UploadTask uploadTask = imagemRef.putFile(imageUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(JogosActivity.this, new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // Log.i("URI",uri.toString());







                        adicionaNoUriDatabase(uri);


                    }
                });



            }
        });


    }

    public   void adicionaNoUriDatabase(Uri uri){

        String nome = editCadastroNome.getText().toString();
        String preco = editCadastroPreco.getText().toString();


        Jogo upload = new Jogo("1",uri.toString(),nome, Double.parseDouble(preco));


        DatabaseReference refUpload = databaseReference.push();
        upload.setId(refUpload.getKey());
        //salvando no database
        refUpload.setValue(upload);



    }

    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(cr.getType(uri));
    }
    public void abrirImagem(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent,PICK_IMAGE_GALLERY);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_GALLERY && resultCode==RESULT_OK
                && data!=null && data.getData()!=null
        ){

            imageUri = data.getData();
            Toast.makeText(this,imageUri.toString(),Toast.LENGTH_LONG).show();
            Glide.with(JogosActivity.this).load(imageUri).into(imageView);
            imageView.setVisibility(View.VISIBLE);
        }
    }
    }

