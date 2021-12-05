package com.flj.freelojobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextView t1, t2, txtRegisterse;
    private EditText et1, et2;
    private ImageView iv1;
    private Button b1;

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Ocultar ActionBar
        getSupportActionBar().hide();

        settings = getSharedPreferences("id", Context.MODE_PRIVATE);

        t1 = (TextView) findViewById(R.id.textView);
//        t2 = (TextView) findViewById(R.id.textView3);
        txtRegisterse = findViewById(R.id.txtviewRegisterse);
        et1 = (EditText) findViewById(R.id.editTextTextPersonName);
        et2 = (EditText) findViewById(R.id.editTextTextPassword);
        iv1 = (ImageView) findViewById(R.id.imageView);
        b1 = (Button) findViewById(R.id.button7);
        b1.setText(R.string.iniciar_sesion);
        t1.setText("     FREELOJOBS\nTrabajo Cerca de Ti");
        t1.setTextSize(20);
        String texto = "Olvide mi contraseña";
//        t2.setText(Html.fromHtml(texto));
//        t2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(et1.getText().toString().equals(settings.getString("user", ""))) {
//                    Toast.makeText(LoginActivity.this,
//                            "Su contraseña es: " + settings.getString("pass", ""),
//                            Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(LoginActivity.this,
//                            "El usuario no existe", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        txtRegisterse.setTextColor(Color.BLUE);
        txtRegisterse.setText(getString(R.string.app_name_login_registrarse));

//        txtRegisterse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences.Editor editor = settings.edit();
//                editor.putString("user", et1.getText().toString());
//                editor.putString("pass", et2.getText().toString());
//                editor.commit();
//
//                et1.setText("");
//                et2.setText("");
//            }
//        });
    }

    public void goToRegistrese(View view){
        Intent intencionRegistrese = new Intent(this, RegistreseActivity.class);
        intencionRegistrese.putExtra("param1","test1");
        intencionRegistrese.putExtra("param2",12345);
        intencionRegistrese.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intencionRegistrese);
    }
    public void iniciarSesion(View view) {
        String usuario = et1.getText().toString();
        String clave = et2.getText().toString();
        if (et1.getText().toString().equals("admin") && et2.getText().toString().equals("admin")) {
            Intent newIntent = new Intent(this, MainActivity.class);
            startActivity(newIntent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
            et1.requestFocus();
        }
        if (et1.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "Por favor, ingrese el usuario", Toast.LENGTH_SHORT).show();
            et1.requestFocus();
        } else if (et2.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "Por favor, ingrese la contraseña", Toast.LENGTH_SHORT).show();
            et2.requestFocus();
        }
    }
}