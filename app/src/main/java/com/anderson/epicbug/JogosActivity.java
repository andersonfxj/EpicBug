package com.anderson.epicbug;

import androidx.appcompat.app.AppCompatActivity;

        import android.media.Image;
        import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class JogosActivity extends AppCompatActivity {
    private EditText editNome;
    private EditText editText;
    private ImageView imageView;
    private Button btnSelecionar, btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogos);
        
    }
}
