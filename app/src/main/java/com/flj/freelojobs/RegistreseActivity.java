package com.flj.freelojobs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class RegistreseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrese);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getParametros();
    }
    public void GuardarDatos(View view) {
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