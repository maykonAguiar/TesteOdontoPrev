package br.com.soccerpoints.meuprojeto.soccerpoints;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdicionarJogador extends Activity implements AdapterView.OnItemSelectedListener  {


    private EditText nameTxt;
    private Spinner sp;
    private ImageView saveBtn;

    List<String> categorias = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_jogador);

        nameTxt = findViewById(R.id.nomeId);
        saveBtn = findViewById(R.id.adicionarJogadorId);

        sp = findViewById(R.id.posicaoId);

        sp.setOnItemSelectedListener(this);


        categorias.add("Gol");
        categorias.add("Linha");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categorias);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(dataAdapter);

        final DBAdapter db = new DBAdapter(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                categorias.clear();


                db.openDB();

                long result = db.add(nameTxt.getText().toString(), null);

                if (result != 0 ){
                    nameTxt.setText("");
                }else {
                    Toast.makeText(getApplicationContext(), "FALHA", Toast.LENGTH_LONG).show();

                }
                db.close();

                startActivity(new Intent(AdicionarJogador.this, ListaJogadores.class));
            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String item = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
