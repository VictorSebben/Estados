package br.ifsul.victor.estados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by victor on 10/22/14.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String nomeBanco = "cadastro.db";
    private static final int versao = 1;

    public DBHelper(Context context) {
        super(context, nomeBanco, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE estado (_id INTEGER PRIMARY KEY, nome TEXT);";
        db.execSQL(sql);

        sql = "CREATE TABLE cidade (_id INTEGER PRIMARY KEY, nome TEXT, id_estado INTEGER, " +
                "FOREIGN KEY (id_estado) REFERENCES estado (_id));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "CREATE TABLE cidade (_id INTEGER PRIMARY KEY, nome TEXT, id_estado INTEGER " +
                "FOREIGN KEY (id_estado) REFERENCES estado (_id));";
    }
}
