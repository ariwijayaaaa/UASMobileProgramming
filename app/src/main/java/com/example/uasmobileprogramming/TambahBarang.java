package com.example.uasmobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahBarang extends AppCompatActivity {
    sql dbHelper;
    Button button;
    EditText namabarang, kategori, hargabeli, hargajual, jumlah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);
        dbHelper = new sql(this);

        namabarang=(EditText) findViewById(R.id.namabarang);
        kategori=(EditText) findViewById(R.id.kategori);
        hargabeli=(EditText) findViewById(R.id.hargabeli);
        hargajual=(EditText) findViewById(R.id.hargajual);
        jumlah=(EditText) findViewById(R.id.jumlah);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String sql = "INSERT INTO barang(nama_barang,kategori,harga_beli,harga_jual,stok)" +
                        "     VALUES('"+ namabarang.getText().toString()+"'," +
                        "            '"+ kategori.getText().toString()+"'," +
                        "            '"+ hargabeli.getText().toString()+"'," +
                        "            '"+ hargajual.getText().toString()+"'," +
                        "            '"+ jumlah.getText().toString()+"')";
                db.execSQL(sql);
                Toast.makeText(getApplicationContext(), "Berhasil Menambah Data", Toast.LENGTH_LONG).show();
                InputBarang.ma.RefreshList();
                finish();
            }
        });

    }
}
