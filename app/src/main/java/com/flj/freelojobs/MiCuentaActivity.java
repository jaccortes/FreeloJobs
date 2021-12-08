package com.flj.freelojobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MiCuentaActivity extends AppCompatActivity {
    private MyDBSQLiteHelper databasehelp;
    private SQLiteDatabase db;
    private Cursor filas;
    private TextView txtnom,txttel,txtmail;
    private SharedPreferences settings;
    private String usuarioGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databasehelp = new MyDBSQLiteHelper(this, vars.nomDB_usu, null, vars.version);

        txtmail = findViewById(R.id.txtcorreousu);
        txttel = findViewById(R.id.txttelfusu);
        txtnom = findViewById(R.id.txtnomusu);

        cargarDatoos();
        setTitle("FREELO JOBS");
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
    public void cargarDatoos(){
        settings = getSharedPreferences("id", Context.MODE_PRIVATE);
        String valor = settings.getString("user_id", "");
        usuarioGlobal=valor;
        Log.i("-------------valorrrrrrrrrrrr:",valor);

        db = databasehelp.getReadableDatabase();
        filas = db.rawQuery("SELECT * FROM "+vars.nomDB_usu+" where usu_id="+usuarioGlobal+"",null);

        while (filas.moveToNext()){
            txtnom.setText(filas.getString(1));
            txttel.setText(filas.getString(2));
            txtmail.setText(filas.getString(3));
        }
        db.close();

    }

}