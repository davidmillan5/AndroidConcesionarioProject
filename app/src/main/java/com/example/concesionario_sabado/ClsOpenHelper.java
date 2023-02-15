package com.example.concesionario_sabado;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class ClsOpenHelper extends SQLiteOpenHelper {
    public ClsOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //Context: Donde se va a implementar la base de datos
        //Name: Nombre de la base de datos
        //Factory: A donde apunta inicialmente, se coloca null
        //Version: Colocamos inicialmnete 1
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table TblCliente(identificacion text primary key, nombre text not null, " +
                "correo text not null,activo text default 'Si')");
        db.execSQL("Create Table TblVehiculo(placa text primary key, modelo text not null, " +
                "marca text not null, activo text default 'Si')");
        db.execSQL("Create Table TblVenta(codigo text primary key, fecha text not null, " +
                "identificacion text not null, placa text not null, activo text default 'Si', " +
                "constraint pk_venta foreign key(identificacion) references TblCliente(identificacion), " +
                "foreign key(placa) references TblVehiculo(placa))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
