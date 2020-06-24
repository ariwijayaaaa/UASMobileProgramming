package com.example.uasmobileprogramming;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class sqlsupplier extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="barangminimarket.db";
    private static final int DATABASE_VERSION=1;

    public sqlsupplier(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql2 = "create table supplier (id integer primary key autoincrement," +
                "            nama_supplier text null," +
                "            alamat text null," +
                "            no_telp text null);";
        Log.d("Data", "onCreate: " + sql2);
        db.execSQL(sql2);

        sql2 = "INSERT INTO supplier(id,nama_supplier,alamat,no_telp)" +
                "            VALUES(1,'CV. Kopi','B','087');";
        db.execSQL(sql2);

        sql2 = "INSERT INTO supplier(id,nama_supplier,alamat,no_telp)" +
                "            VALUES(2,'CV. Ayam','A','085');";
        db.execSQL(sql2);









        String sql = "create table barang (id integer primary key autoincrement," +
                "            nama_barang text null," +
                "            kategori text null," +
                "            harga_beli text null," +
                "            harga_jual text null," +
                "            stok text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

        sql = "INSERT INTO barang(id,nama_barang,kategori,harga_beli,harga_jual,stok)" +
                "            VALUES(1,'Kopi ABC','Kopi','1500','2000','2');";
        db.execSQL(sql);

        sql = "INSERT INTO barang(id,nama_barang,kategori,harga_beli,harga_jual,stok)" +
                "          VALUES(2,'Kopi Kapal Api','Kopi','1000','1500','2');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
