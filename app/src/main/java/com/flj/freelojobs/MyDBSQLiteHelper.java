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
        db.execSQL("CREATE TABLE producto(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion TEXT, categoria TEXT, marca TEXT, proveedor TEXT)");
        db.execSQL("CREATE TABLE imagenes(_id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT, img BLOB)");

        db.execSQL("CREATE TABLE flj_users(usu_id INTEGER PRIMARY KEY AUTOINCREMENT, use_name TEXT, use_phon TEXT, use_mail TEXT, use_pass TEXT)");
        db.execSQL("CREATE TABLE flj_freelos (frj_id INTEGER PRIMARY KEY AUTOINCREMENT, frj_type TEXT, frj_desc TEXT, frj_pric TEXT, frj_lat TEXT, frj_lon TEXT)");
        db.execSQL("CREATE TABLE flj_myfreelos (frj_id INTEGER PRIMARY KEY AUTOINCREMENT, mfj_usu_id TEXT, mfj_frj_id TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int verDBAnterior, int verDBNueva) {
        db.execSQL("DROP TABLE IF EXISTS producto");
        db.execSQL("DROP TABLE IF EXISTS imagenes");
        db.execSQL("CREATE TABLE producto(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion TEXT," +
                "categoria TEXT, marca TEXT, proveedor TEXT)");
        db.execSQL("CREATE TABLE imagenes(_id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT, img BLOB)");

        db.execSQL("DROP TABLE IF EXISTS flj_users");
        db.execSQL("DROP TABLE IF EXISTS flj_freelos");
        db.execSQL("DROP TABLE IF EXISTS flj_myfreelos");

        db.execSQL("CREATE TABLE flj_users(usu_id INTEGER PRIMARY KEY AUTOINCREMENT, use_name TEXT, use_phon TEXT, use_mail TEXT, use_pass TEXT)");
        db.execSQL("CREATE TABLE flj_freelos (frj_id INTEGER PRIMARY KEY AUTOINCREMENT, frj_type TEXT, frj_desc TEXT, frj_pric TEXT, frj_lat TEXT, frj_lon TEXT)");
        db.execSQL("CREATE TABLE flj_myfreelos (frj_id INTEGER PRIMARY KEY AUTOINCREMENT, mfj_usu_id TEXT, mfj_frj_id TEXT)");

    }
}
