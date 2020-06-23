package com.example.uasmobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditBarang extends AppCompatActivity {
    EditText namabarang, kategori, hargabeli, hargajual, jumlah;
    Button button;
    sql dbHelper;
    protected Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        dbHelper = new sql(this);
        namabarang=(EditText) findViewById(R.id.namabarang);
        kategori=(EditText) findViewById(R.id.kategori);
        hargabeli=(EditText) findViewById(R.id.hargabeli);
        hargajual=(EditText) findViewById(R.id.hargajual);
        jumlah=(EditText) findViewById(R.id.jumlah);
        button = (Button) findViewById(R.id.button);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        final String sql = "SELECT * from barang where nama_barang = '"+ getIntent().getStringExtra("nama_barang")+"'";
        cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            namabarang.setText(cursor.getString(1).toString());
            kategori.setText(cursor.getString(2).toString());
            hargabeli.setText(cursor.getString(3).toString());
            hargajual.setText(cursor.getString(4).toString());
            jumlah.setText(cursor.getString(5).toString());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String sql = "UPDATE barang  SET nama_barang = '"+namabarang.getText().toString()+"'," +
                        "                        kategori    = '"+kategori.getText().toString()+"'," +
                        "                        harga_beli  = '"+hargabeli.getText().toString()+"'," +
                        "                        harga_jual  = '"+hargajual.getText().toString()+"'," +
                        "                        stok      = '"+jumlah.getText().toString()+"'" +
                        "WHERE nama_barang = '"+getIntent().getStringExtra("nama_barang")+"'";
                db.execSQL(sql);
                Toast.makeText(getApplicationContext(), "Berhasil Mengubah Data!", Toast.LENGTH_LONG).show();
                InputBarang.ma.RefreshList();
                finish();
            }
        });
    }
}
