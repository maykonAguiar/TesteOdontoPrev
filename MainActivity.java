package br.com.soccerpoints.meuprojeto.soccerpoints;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.MissingFormatArgumentException;

public class MainActivity extends AppCompatActivity {

    private ImageView botaoIniciar;
    private ImageView adicionarJogadotTela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoIniciar = (ImageView) findViewById(R.id.adicionarInicioId);
        adicionarJogadotTela = (ImageView) findViewById(R.id.ListaJogadorId);

        botaoIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, AdicionarJogador.class));
            }
        });

        adicionarJogadotTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, ListaJogadores.class));
            }
        });
    }
}
