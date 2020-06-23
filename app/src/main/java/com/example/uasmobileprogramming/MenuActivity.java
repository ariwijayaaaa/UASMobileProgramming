package com.example.uasmobileprogramming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    CardView btnProfile, btnInputBarang, btnInputSupplier;
    Button btnKeluar;
    SharedPreference sp;
    Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnProfile = (CardView) findViewById(R.id.profilemenu);
        btnInputBarang = (CardView) findViewById(R.id.inputbarang);
        btnInputSupplier = (CardView) findViewById(R.id.inputsupplier);
        btnKeluar = (Button) findViewById(R.id.keluar);
        sp = new SharedPreference();

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(MenuActivity.this, Profile.class);
                startActivity(inten);
            }
        });

        btnInputBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(MenuActivity.this, InputBarang.class);
                startActivity(inten);
            }
        });

        btnInputSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(MenuActivity.this, InputSupplier.class);
                startActivity(inten);
            }
        });

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.clearSharedPreference(context);
                finish();
            }
        });
    }

}
