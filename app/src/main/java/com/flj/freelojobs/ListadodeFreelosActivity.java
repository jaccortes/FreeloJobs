package com.flj.freelojobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListadodeFreelosActivity extends AppCompatActivity {

    private MyDBSQLiteHelper databasehelp;
    private SQLiteDatabase db;
    private Cursor filas;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadode_freelos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Listado de Todos los Freelo's");

        databasehelp = new MyDBSQLiteHelper(this, vars.nomDB_flj, null, vars.version);

        lv = findViewById(R.id.listadefeelostotal);
        ArrayList<String> listado = new ArrayList<>();

        db = databasehelp.getReadableDatabase();

        filas = db.rawQuery("SELECT * FROM "+vars.nomDB_flj,null);

        while (filas.moveToNext()){
            listado.add("Id: "+filas.getInt(0)+" - Tipo: "+filas.getString(1)+"\n Desc : "+filas.getString(2)+"\n Precio: "+filas.getString(3)+"\n Latitud: "+filas.getString(4)+"\n Longitud: "+filas.getString(5));
        }
        db.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listado);
        lv.setAdapter(adapter);
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