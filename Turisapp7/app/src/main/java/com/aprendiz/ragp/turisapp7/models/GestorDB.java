package com.aprendiz.ragp.turisapp7.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aprendiz.ragp.turisapp7.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GestorDB extends SQLiteOpenHelper {
    public GestorDB(Context context) {
        super(context, "lugares.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE LUGARES (IMAGEN INTEGER, NOMBRE TEXT, DESCRIPCIONC TEXT, UBICACION TEXT, DESCRIPCION TEXT, LATITUD INTEGER, LONGITUD INTEGER, LUGAR TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inputSitios(Context context) throws IOException {
        SQLiteDatabase db = getWritableDatabase();
        String linea;
        InputStream is = context.getResources().openRawResource(R.raw.sitios);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is!=null){
            int i=0;
            while ((linea=reader.readLine())!=null){
                String [] strings = linea.split(";");
                ContentValues values = new ContentValues();
                values.put("IMAGEN",Constans.imagenesSitios[i]);
                values.put("NOMBRE",strings[0]);
                values.put("DESCRIPCIONC",strings[1]);
                values.put("UBICACION",strings[2]);
                values.put("DESCRIPCION",strings[3]);
                values.put("LATITUD",strings[4]);
                values.put("LONGITUD",strings[5]);
                values.put("LUGAR","sitio");
                db.insert("LUGARES",null,values);
                i++;
            }
        }

        db.close();
    }


    public void inputHoteles(Context context) throws IOException {
        SQLiteDatabase db = getWritableDatabase();
        String linea;
        InputStream is = context.getResources().openRawResource(R.raw.sitios);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is!=null){
            int i=0;
            while ((linea=reader.readLine())!=null){
                String [] strings = linea.split(";");
                ContentValues values = new ContentValues();
                values.put("IMAGEN",Constans.imagenesHoteles[i]);
                values.put("NOMBRE",strings[0]);
                values.put("DESCRIPCIONC",strings[1]);
                values.put("UBICACION",strings[2]);
                values.put("DESCRIPCION",strings[3]);
                values.put("LATITUD",strings[4]);
                values.put("LONGITUD",strings[5]);
                values.put("LUGAR","hotel");
                db.insert("LUGARES",null,values);
                i++;
            }
        }

        db.close();
    }

    public void inputRestaurantes(Context context) throws IOException {
        SQLiteDatabase db = getWritableDatabase();
        String linea;
        InputStream is = context.getResources().openRawResource(R.raw.sitios);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is!=null){
            int i=0;
            while ((linea=reader.readLine())!=null){
                String [] strings = linea.split(";");
                ContentValues values = new ContentValues();
                values.put("IMAGEN",Constans.imagenesRestaurantes[i]);
                values.put("NOMBRE",strings[0]);
                values.put("DESCRIPCIONC",strings[1]);
                values.put("UBICACION",strings[2]);
                values.put("DESCRIPCION",strings[3]);
                values.put("LATITUD",strings[4]);
                values.put("LONGITUD",strings[5]);
                values.put("LUGAR","restaurante");
                db.insert("LUGARES",null,values);
                i++;
            }
        }

        db.close();
    }

    public List<Lugares> sitiosList(){
        List<Lugares> results = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LUGARES WHERE LUGAR = 'sitio'",null);
        if (cursor.moveToFirst()){
            do {
                Lugares lugares = new Lugares();
                lugares.setImagen(cursor.getInt(0));
                lugares.setNombre(cursor.getString(1));
                lugares.setDescripcionc(cursor.getString(2));
                lugares.setUbicacion(cursor.getString(3));
                lugares.setDescripcion(cursor.getString(4));
                lugares.setLatitud(cursor.getDouble(5));
                lugares.setLongitud(cursor.getDouble(6));
                lugares.setLugar(cursor.getString(7));
                results.add(lugares);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return results;
    }


    public List<Lugares> hotelesList(){
        List<Lugares> results = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LUGARES WHERE LUGAR = 'hotel'",null);
        if (cursor.moveToFirst()){
            do {
                Lugares lugares = new Lugares();
                lugares.setImagen(cursor.getInt(0));
                lugares.setNombre(cursor.getString(1));
                lugares.setDescripcionc(cursor.getString(2));
                lugares.setUbicacion(cursor.getString(3));
                lugares.setDescripcion(cursor.getString(4));
                lugares.setLatitud(cursor.getDouble(5));
                lugares.setLongitud(cursor.getDouble(6));
                lugares.setLugar(cursor.getString(7));
                results.add(lugares);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return results;
    }

    public List<Lugares> restaurantesList(){
        List<Lugares> results = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LUGARES WHERE LUGAR = 'restaurante'",null);
        if (cursor.moveToFirst()){
            do {
                Lugares lugares = new Lugares();
                lugares.setImagen(cursor.getInt(0));
                lugares.setNombre(cursor.getString(1));
                lugares.setDescripcionc(cursor.getString(2));
                lugares.setUbicacion(cursor.getString(3));
                lugares.setDescripcion(cursor.getString(4));
                lugares.setLatitud(cursor.getDouble(5));
                lugares.setLongitud(cursor.getDouble(6));
                lugares.setLugar(cursor.getString(7));
                results.add(lugares);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return results;
    }


}
