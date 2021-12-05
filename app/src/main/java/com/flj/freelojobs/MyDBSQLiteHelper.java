package com.flj.freelojobs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBSQLiteHelper extends SQLiteOpenHelper {
    public MyDBSQLiteHelper(Context context, String nomDB, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nomDB, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE producto(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion TEXT," +
                "categoria TEXT, marca TEXT, proveedor TEXT)");
        db.execSQL("CREATE TABLE imagenes(_id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT, img BLOB)");
        db.execSQL("CREATE TABLE FLJ_usuarios(_id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, img BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int verDBAnterior, int verDBNueva) {
        db.execSQL("DROP TABLE IF EXISTS producto");
        db.execSQL("DROP TABLE IF EXISTS imagenes");
        db.execSQL("DROP TABLE IF EXISTS FLJ_usuarios");
        db.execSQL("CREATE TABLE producto(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion TEXT," +
                "categoria TEXT, marca TEXT, proveedor TEXT)");
        db.execSQL("CREATE TABLE imagenes(_id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT, img BLOB)");
        db.execSQL("CREATE TABLE FLJ_usuarios(_id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, img BLOB)");
    }
}
