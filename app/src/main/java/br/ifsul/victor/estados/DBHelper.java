package br.ifsul.victor.estados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.ifsul.victor.model.Estado;

/**
 * Created by victor on 10/22/14.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String nomeBanco = "cadastro.db";
    private static final int versao = 1;

    public DBHelper(Context context) {
        super(context, nomeBanco, null, versao);
    }

    public List<Estado> getAllEstados() {
        List<Estado> estados = new ArrayList<Estado>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM estado";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                estados.add(new Estado(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        // closing the connection
        cursor.close();
        db.close();

        return estados;
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
