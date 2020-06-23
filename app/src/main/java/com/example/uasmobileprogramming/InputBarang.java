package com.example.uasmobileprogramming;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InputBarang extends AppCompatActivity {
    String[] daftar;
    ListView ListView01;
    sql dbHelper;
    protected Cursor cursor;
    public static InputBarang ma;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_barang);

        ma = this;
        dbHelper = new sql(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("Select * FROM barang",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc<cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc]=cursor.getString(1).toString();
        }

        ListView01= (ListView) findViewById(R.id.ListView01);
        ListView01.setAdapter(new ArrayAdapter<Object>(this,android.R.layout.simple_list_item_1,daftar));
        ListView01.setSelected(true);
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();

        ListView01.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Edit","Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(InputBarang.this);
                builder.setTitle("Pilih !");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent inten= new Intent(InputBarang.this, EditBarang.class);
                                inten.putExtra("nama_barang", selection);
                                startActivity(inten);
                                break;
                            case 1:
                                SQLiteDatabase db=dbHelper.getWritableDatabase();
                                String sql="DELETE from barang where nama_barang = '" + selection + "'";
                                db.execSQL(sql);
                                RefreshList();
                                break;

                        }
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        menu.add(0, 1, 0, "Tambah");
        menu.add(0, 2, 0, "Refresh");
        menu.add(0, 3, 0, "Exit");
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item ) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(InputBarang.this,TambahBarang.class);
                startActivity(intent);
                return true;
            case 2:
                RefreshList();
                return true;
            case 3:
                finish();
                return true;
        }
        return false;
    }
}
