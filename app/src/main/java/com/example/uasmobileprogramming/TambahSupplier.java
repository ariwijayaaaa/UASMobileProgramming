package com.example.uasmobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahSupplier extends AppCompatActivity {

    sql dbHelper;
    Button button;
    EditText namasupplier, alamat, notelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_supplier);
        dbHelper = new sql(this);

        namasupplier=(EditText) findViewById(R.id.namasupplier);
        alamat=(EditText) findViewById(R.id.alamat);
        notelp=(EditText) findViewById(R.id.notelp);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String sql = "INSERT INTO supplier(nama_supplier,alamat,no_telp)" +
                        "     VALUES('"+ namasupplier.getText().toString()+"'," +
                        "            '"+ alamat.getText().toString()+"'," +
                        "            '"+ notelp.getText().toString()+"')";
                db.execSQL(sql);
                Toast.makeText(getApplicationContext(), "Berhasil Menambah Data", Toast.LENGTH_LONG).show();
                InputSupplier.is.RefreshList();
                finish();
            }
        });
    }
}
