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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BuscarFreeloActivity extends AppCompatActivity {

    private MyDBSQLiteHelper databasehelp;
    private SQLiteDatabase db;
    private Cursor filas;
    private ListView lv;
    AlertDialog.Builder builder;
    private ContentValues cv;
    private SharedPreferences settings;
    private String usuarioGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_freelo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("FREELO JOBS");

        settings = getSharedPreferences("id", Context.MODE_PRIVATE);
        String valor = settings.getString("user_id", "");
        usuarioGlobal=valor;

        databasehelp = new MyDBSQLiteHelper(this, vars.nomDB_flj, null, vars.version);

        lv = findViewById(R.id.listadefeelostotal);
        ArrayList<String> listado = new ArrayList<>();

        db = databasehelp.getReadableDatabase();

        filas = db.rawQuery("SELECT * FROM "+vars.nomDB_flj,null);

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

                builder = new AlertDialog.Builder(BuscarFreeloActivity.this);
                builder.setMessage(R.string.dialog_message)
                        .setTitle(R.string.dialog_title)
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String valor = lv.getItemAtPosition(pos).toString();
                                String valorfrelo = valor.substring(0,1);

                                db  = databasehelp.getWritableDatabase();
                                cv = new ContentValues();

                                cv.put("mfj_usu_id",usuarioGlobal);
                                cv.put("mfj_frj_id",valorfrelo);

                                long reg = db.insert(vars.nomDB_mfj.toString(),null,cv);

                                if(reg !=-1){
                                    Toast.makeText(BuscarFreeloActivity.this,"!!!POSTULACION REALIZADA CON EXITO!!!"+valor, Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(BuscarFreeloActivity.this, "Error al Crear el Freelo", Toast.LENGTH_SHORT).show();
                                }

                                db.close();
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

        Spinner tipodetrab = findViewById(R.id.tipotrab2);

        tipodetrab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                db = databasehelp.getReadableDatabase();

                lv = findViewById(R.id.listadefeelostotal);

                ArrayList<String> listadox = new ArrayList<>();
                ArrayList<String> listadod = new ArrayList<>();

                ArrayAdapter<String> adapterx = new ArrayAdapter<String>(
                        BuscarFreeloActivity.this, android.R.layout.simple_list_item_1, listadox);
                lv.setAdapter(adapterx);

                String tit = tipodetrab.getSelectedItem().toString();

                filas = db.rawQuery("SELECT * FROM " + vars.nomDB_flj + " WHERE frj_type='"+tit+"'", null);

                while (filas.moveToNext()) {
                    listadod.add(filas.getInt(0) + " - Tipo: " + filas.getString(1) + "\n" + filas.getString(2) + "\n Precio: " + filas.getString(3) + "\n Latitud: " + filas.getString(4) + "\n Longitud: " + filas.getString(5));
                }
                db.close();

                ArrayAdapter<String> adapterd = new ArrayAdapter<String>(
                        BuscarFreeloActivity.this, android.R.layout.simple_list_item_1, listadod);

                lv.setAdapter(adapterd);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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