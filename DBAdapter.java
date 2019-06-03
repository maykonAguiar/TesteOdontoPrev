package br.com.soccerpoints.meuprojeto.soccerpoints;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by maykon on 01/02/2018.
 */

public class DBAdapter {

    static final String ROWID = "id";
    static final String NAME = "nome";
    static final String POSITION="posicao";

    static final String TAG = "DBSpinner";

    static final String DBNAME = "s_DB";
    static final String TBNAME = "s_TB";
    static final int DBVERSION = '1';

    static final String CREATE_TB= "CREATE TABLE s_TB (id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nome TEXT NOT NULL, posicao TEXT NOT NULL);";

    final Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter (Context ctx){
        this.c = ctx;
        helper = new DBHelper(c);
    }

    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper (Context context){
            super(context, DBNAME, null, DBVERSION );
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TB);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int olderVersion, int newVersion) {
            Log.w(TAG, "Upgrading DB");
            db.execSQL("DROP TABLE IF EXISTS s_TB");
            onCreate(db);

        }
    }

    public DBAdapter openDB(){
        try {
            db = helper.getWritableDatabase();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return this;
    }

    public void close(){
        helper.close();
    }

    public long add (String name, String pos){
        try {
            ContentValues cv = new ContentValues();
            cv.put(NAME, name);
            cv.put(POSITION, pos);

            return db.insert(TBNAME, ROWID, cv);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public Cursor getAllValues (){
        String [] columns = {ROWID, NAME, POSITION};

        return db.query(TBNAME, columns, null, null, null, null, null);
    }
}
