package com.example.uasmobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditSupplier extends AppCompatActivity {

    EditText namasupplier, alamat, notelp;
    Button button;
    sqlsupplier dbHelper;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_supplier);

        dbHelper = new sqlsupplier(this);
        namasupplier=(EditText) findViewById(R.id.namasupplier);
        alamat=(EditText) findViewById(R.id.alamat);
        notelp=(EditText) findViewById(R.id.notelp);
        button = (Button) findViewById(R.id.button);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        final String sql = "SELECT * from supplier where nama_supplier = '"+ getIntent().getStringExtra("nama_supplier")+"'";
        cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            namasupplier.setText(cursor.getString(1).toString());
            alamat.setText(cursor.getString(2).toString());
            notelp.setText(cursor.getString(3).toString());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String sql = "UPDATE supplier  SET nama_supplier = '"+namasupplier.getText().toString()+"'," +
                        "                          alamat    = '"+alamat.getText().toString()+"'," +
                        "                          no_telp      = '"+notelp.getText().toString()+"'" +
                        "WHERE nama_supplier = '"+getIntent().getStringExtra("nama_supplier")+"'";
                db.execSQL(sql);
                Toast.makeText(getApplicationContext(), "Berhasil Mengubah Data!", Toast.LENGTH_LONG).show();
                InputSupplier.is.RefreshList();
                finish();
            }
        });
    }
}
