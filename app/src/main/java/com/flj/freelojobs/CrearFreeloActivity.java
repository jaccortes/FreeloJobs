package com.flj.freelojobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CrearFreeloActivity extends AppCompatActivity {

    private MyDBSQLiteHelper databasehelp;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Spinner inputtit;
    private EditText inputdes,inputpre,inputlat,inputlon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_freelo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("FREELO JOBS");
        databasehelp = new MyDBSQLiteHelper(this, vars.nomDB_flj, null, vars.version);

    }
    public void GuardarDatos(View view) {

        inputtit = (Spinner) findViewById(R.id.tipotrab);
        inputdes = (EditText) findViewById(R.id.editTextTrabajo);
        inputpre = (EditText) findViewById(R.id.editTextPrecio);
        inputlat = (EditText) findViewById(R.id.editTextlatitud);
        inputlon = (EditText) findViewById(R.id.editTextLongitud);

        String tit = inputtit.getSelectedItem().toString();
        String des = inputdes.getText().toString();
        String pre = inputpre.getText().toString();

        String lat = inputlat.getText().toString();
        String lon = inputlon.getText().toString();

        db  = databasehelp.getWritableDatabase();
        cv = new ContentValues();

        cv.put("frj_type",tit);
        cv.put("frj_desc",des);
        cv.put("frj_pric",pre);
        cv.put("frj_lat",lat);
        cv.put("frj_lon",lon);

        long reg = db.insert(vars.nomDB_flj.toString(),null,cv);

        if(reg !=-1){
            Toast.makeText(this, "Freelo Creado con Exito!!", Toast.LENGTH_SHORT).show();
            inputtit.setSelection(0);
            inputdes.setText("");
            inputpre.setText("");
        }
        else{
            Toast.makeText(this, "Error al Crear Frelo", Toast.LENGTH_SHORT).show();
        }

        db.close();

    }
    public void goToMain(View view){
        Intent intencionVolverAMain = new Intent(this, MainActivity.class);
        startActivity(intencionVolverAMain);
    }
    public void botonVolverdelaccionBar(){
        finish();
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int codigoqdevuelve = menuItem.getItemId();
        if(codigoqdevuelve == android.R.id.home){
            botonVolverdelaccionBar();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}