package br.com.soccerpoints.meuprojeto.soccerpoints;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListaJogadores extends AppCompatActivity {

    private ListView listaJogadores;

    private SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jogadores);

        listaJogadores = (ListView) findViewById(R.id.listaJogadoresId);

        bd = this.openOrCreateDatabase("s_DB", Context.MODE_PRIVATE, null);

        montarListaRegistros();

    }

    private void montarListaRegistros(){
        Cursor registro =
                bd.query("s_TB", null, null, null, null, null, null);
        String nomeTabela[] = new String[] {"nome", "posicao"};
        int nomeCampos[] = new int[] {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                android.R.layout.two_line_list_item,
                registro,
                nomeTabela,
                nomeCampos);

        listaJogadores.setAdapter(adapter);

    }
}
