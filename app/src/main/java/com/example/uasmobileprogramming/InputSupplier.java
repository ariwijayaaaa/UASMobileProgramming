package com.example.uasmobileprogramming;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InputSupplier extends AppCompatActivity {

    String[] daftar;
    ListView ListView02;
    sqlsupplier dbHelper;
    protected Cursor cursor;
    public static InputSupplier is;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_supplier);

        is = this;
        dbHelper = new sqlsupplier(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * FROM supplier",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc<cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc]=cursor.getString(1).toString();
        }

        ListView02= (ListView) findViewById(R.id.ListView02);
        ListView02.setAdapter(new ArrayAdapter<Object>(this,android.R.layout.simple_list_item_1,daftar));
        ListView02.setSelected(true);
        ((ArrayAdapter) ListView02.getAdapter()).notifyDataSetInvalidated();

        ListView02.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Edit","Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(InputSupplier.this);
                builder.setTitle("Pilih !");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent inten= new Intent(InputSupplier.this, EditSupplier.class);
                                inten.putExtra("nama_supplier", selection);
                                startActivity(inten);
                                break;
                            case 1:
                                SQLiteDatabase db=dbHelper.getWritableDatabase();
                                String sql="DELETE from supplier where nama_supplier = '" + selection + "'";
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
                Intent intent = new Intent(InputSupplier.this,TambahSupplier.class);
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
