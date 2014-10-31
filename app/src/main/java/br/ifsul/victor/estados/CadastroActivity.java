package br.ifsul.victor.estados;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CadastroActivity extends Activity {
    private EditText estado;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        estado = (EditText) findViewById(R.id.edtEstado);
        dbHelper = new DBHelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void gravarEstado(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // conexão com o banco
        ContentValues values = new ContentValues();
        values.put("nome", estado.getText().toString());

        long result = db.insert("estado", "_id", values);

        if (result != -1) {
            Toast.makeText(this, "Sucesso", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Falhou", Toast.LENGTH_LONG).show();
        }

        db.close();
        estado.setText("");
    }
}
