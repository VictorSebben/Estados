package br.ifsul.victor.estados;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.ifsul.victor.model.Estado;


public class CadastroCidadeActivity extends Activity {
    private EditText nome;
    private Spinner estado;
    private DBHelper dbHelper;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cidade);

        nome = (EditText) findViewById(R.id.edtNome);
        estado = (Spinner) findViewById(R.id.spnEstados);
        dbHelper = new DBHelper(this);

        // spinner para selecionar estado
        spinner = (Spinner) findViewById(R.id.spnEstados);
        this.loadSpinnerData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cadastro_cidade, menu);
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

    public void gravarCidade(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // conexão com o banco
        ContentValues values = new ContentValues();
        values.put("nome", nome.getText().toString());

        Estado est = (Estado) spinner.getSelectedItem();

        values.put("id_estado", est.getId());

        long result = db.insert("cidade", "_id", values);

        if (result != -1) {
            Toast.makeText(this, "Sucesso", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Falhou", Toast.LENGTH_LONG).show();
        }

        db.close();
        nome.setText("");
    }

    private void loadSpinnerData() {
        List<Estado> estados = dbHelper.getAllEstados();

        // criar Adapter para o spinner
        ArrayAdapter<Estado> dataAdapter = new ArrayAdapter<Estado>(this,
                android.R.layout.simple_spinner_item, estados);

        spinner.setAdapter(dataAdapter);
    }
}
