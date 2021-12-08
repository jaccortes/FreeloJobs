package com.flj.freelojobs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MisFreelosActivity extends AppCompatActivity {

    private MyDBSQLiteHelper databasehelp;
    private SQLiteDatabase db;
    private Cursor filas;
    private ListView lv;
    private SharedPreferences settings;
    private String usuarioGlobal;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_freelos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("FREELO JOBS");

        settings = getSharedPreferences("id", Context.MODE_PRIVATE);
        String valor = settings.getString("user_id", "");
        usuarioGlobal=valor;

        databasehelp = new MyDBSQLiteHelper(this, vars.nomDB_flj, null, vars.version);

        lv = findViewById(R.id.listadefeelostotal);
        ArrayList<String> listado = new ArrayList<>();

        db = databasehelp.getReadableDatabase();

        filas = db.rawQuery("SELECT * FROM "+vars.nomDB_flj+" WHERE frj_id IN (SELECT mfj_frj_id FROM "+vars.nomDB_mfj+" WHERE mfj_usu_id="+usuarioGlobal+")",null);

        while (filas.moveToNext()){
            listado.add(filas.getInt(0)+" - Tipo: "+filas.getString(1)+"\n"+filas.getString(2)+"\n Precio: "+filas.getString(3)+"\n Latitud: "+filas.getString(4)+"\n Longitud: "+filas.getString(5));
        }
        db.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, listado);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                builder = new AlertDialog.Builder(MisFreelosActivity.this);
                builder.setMessage(R.string.dialog_messageDel)
                        .setTitle(R.string.dialog_title)
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String valor = lv.getItemAtPosition(pos).toString();
                                String valorfrelo = valor.substring(0,1);

                                db  = databasehelp.getWritableDatabase();
                                //            int reg = db.delete("producto", "nombre=" + "'" + nom + "'", null);
                                String[] args = new String[]{valorfrelo};
                                int reg = db.delete(vars.nomDB_mfj, "mfj_frj_id=?", args);
                                if (reg == 0)
                                    Toast.makeText(MisFreelosActivity.this, "La Postulacion NO se pudo Eliminar", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(MisFreelosActivity.this, "!!!Postulacion Freelo Eliminada!!!", Toast.LENGTH_SHORT).show();

                                db.close();
                                finish();
                                startActivity(getIntent());
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
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