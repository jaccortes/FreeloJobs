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

public class listausuariosActivity extends AppCompatActivity {

    private MyDBSQLiteHelper databasehelp;
    private SQLiteDatabase db;
    private Cursor filas;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listausuarios);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Listado de Todos los Usuarios");

        databasehelp = new MyDBSQLiteHelper(this, vars.nomDB_usu, null, vars.version);

        lv = findViewById(R.id.listadefeelostotal);
        ArrayList<String> listado = new ArrayList<>();

        db = databasehelp.getReadableDatabase();

        filas = db.rawQuery("SELECT * FROM "+vars.nomDB_usu,null);

        while (filas.moveToNext()){
            listado.add("Id: "+filas.getInt(0)+" - "+filas.getString(1)+"\n Telf :"+filas.getString(2)+"\n Mail: "+filas.getString(3)+"\n PassWord :"+filas.getString(4));
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