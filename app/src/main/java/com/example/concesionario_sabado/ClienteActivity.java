package com.example.concesionario_sabado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteActivity extends AppCompatActivity {

    EditText jetidentificacion, jetnombre, jetcorreo;
    CheckBox jcbactivo;

    String identificacion, nombre, correo;

    ClsOpenHelper admin = new ClsOpenHelper(this,"Concesionario.db",null,1);

    long respuesta;


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

    public void Guardar(View view){
        identificacion = jetidentificacion.getText().toString();
        nombre = jetnombre.getText().toString();
        correo = jetcorreo.getText().toString();
        if(identificacion.isEmpty() || nombre.isEmpty() || correo.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetidentificacion.requestFocus();
        }else{
            SQLiteDatabase db = admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("identificacion",identificacion);
            registro.put("nombre",nombre);
            registro.put("correo",correo);
            respuesta = db.insert("TblCliente", null,registro);
            if(respuesta == 0){
                Toast.makeText(this, "Error guardando registro", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
                limpiar_campos();
            }

            db.close();
        }
    } //Fin del metodo guardar


    public void Consultar(View view){
        //Validando que haya una identificacion
        identificacion = jetidentificacion.getText().toString();
        if(!identificacion.isEmpty()){
            SQLiteDatabase db = admin.getReadableDatabase();
            Cursor fila = db.rawQuery("select * from TblCliente where identificacion ='"+identificacion+"'",null);
            if(fila.moveToNext()){
                jetnombre.setText(fila.getString(1));
                jetcorreo.setText(fila.getString(2));
                jcbactivo.setChecked(fila.getString(3).equals("Si"));
            }else{
                Toast.makeText(this, "Registro no hallado", Toast.LENGTH_SHORT).show();
                db.close();
            }
        }else{
            Toast.makeText(this, "Identificacion es requerida para consultar", Toast.LENGTH_SHORT).show();
            jetidentificacion.requestFocus();
        }
    }


    private void limpiar_campos(){
        jetidentificacion.setText("");
        jetnombre.setText("");
        jetcorreo.setText("");
        jetidentificacion.requestFocus();

    }

}