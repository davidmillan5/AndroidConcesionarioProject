package com.example.concesionario_sabado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class ClienteActivity extends AppCompatActivity {

    EditText jetidentificacion, jetnombre, jetcorreo;
    CheckBox jcbactivo;

    String identificacion, nombre, correo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        //Ocultar la barra de titulo por defecto y voy a asociar objetos Java con XML
        getSupportActionBar().hide();
        jetidentificacion = findViewById(R.id.etidentificacion);
        jetnombre = findViewById(R.id.etnombre);
        jetcorreo = findViewById(R.id.etcorreo);
        jcbactivo = findViewById(R.id.cbactivo);

    }
}