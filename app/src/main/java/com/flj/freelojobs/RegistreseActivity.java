package com.flj.freelojobs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistreseActivity extends AppCompatActivity {
    private MyDBSQLiteHelper databasehelp;
    private SQLiteDatabase db;
    private ContentValues cv;


    private EditText inputnom,inputtel,inputmail,inputpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrese);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getParametros();
        setTitle("FREELO JOBS");
        databasehelp = new MyDBSQLiteHelper(this, vars.nomDB_usu, null, vars.version);

        inputnom = findViewById(R.id.nombreusu);
        inputtel = findViewById(R.id.telefonousu);
        inputmail =  findViewById(R.id.correo);
        inputpass =  findViewById(R.id.password);

    }
    public void GuardarDatos(View view) {

        String nom = inputnom.getText().toString();
        String tel = inputtel.getText().toString();
        String mai = inputmail.getText().toString();
        String pas = inputpass.getText().toString();

        db  = databasehelp.getWritableDatabase();
        cv = new ContentValues();

        cv.put("use_name",nom);
        cv.put("use_phon",tel);
        cv.put("use_mail",mai);
        cv.put("use_pass",pas);

        long reg = db.insert(vars.nomDB_usu.toString(),null,cv);

        if(reg !=-1){
            Toast.makeText(this, "Usuario Creado con Exito!!", Toast.LENGTH_SHORT).show();
            inputpass.setText("");
            inputnom.setText("");
            inputmail.setText("");
            inputtel.setText("");
        }
        else{
            Toast.makeText(this, "Error al Crear Usuario", Toast.LENGTH_SHORT).show();
        }

        db.close();

    }
    public void getParametros(){
        Bundle capturalosextras = getIntent().getExtras();
        String parametrorecibido1 = capturalosextras.getString("param1");
        Integer parametrorecibido2 = capturalosextras.getInt("param2");
        Toast.makeText(this,parametrorecibido1+" "+parametrorecibido2,Toast.LENGTH_SHORT);
        Log.i("info",parametrorecibido1+" "+parametrorecibido2);
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