package com.example.libri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.SQLHelper;
import helpers.Login;

public class MainActivity extends AppCompatActivity {

    private EditText txtLogin;
    private EditText txtSenha;
    private Button btnLogar;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        btnLogar = findViewById(R.id.btnEntrar);
        btnCadastrar = findViewById(R.id.cadastrarUsuarioLogin);

        btnCadastrar.setOnClickListener(view -> {
            /** A INTENT REQUER DOIS PARÂMETROS: O CONTEXTO EM QUE ESTAMOS (ACTIVITY) E A CLASSE **/
//            Intent telaCadastro = new Intent(MainActivity.this,
//                    CadastroUsuario.class);
//
//            startActivity(telaCadastro);
            startActivity(new Intent(MainActivity.this, CadastroUsuario.class));

        });

        btnLogar.setOnClickListener(view -> {

            String login = txtLogin.getText().toString();
            String senha = txtSenha.getText().toString();

            int cod_usuario = SQLHelper.getInstance(this).login(login, senha);

            if (cod_usuario > 0) {

                Login.setCod_usuario(cod_usuario);

                startActivity(
                        new Intent(MainActivity.this,
                                FeedLivros.class)
                );

            } else {

                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();

            }

        });

    }
}