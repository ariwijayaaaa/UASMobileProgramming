package com.example.uasmobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView textuser;
    Button BtnKeluar;
    SharedPreference sp;
    Activity context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sp = new SharedPreference();
        textuser=(TextView) findViewById(R.id.txtnim);
        String username;
        username= sp.getValue(context, "username");

        textuser.setText(username);

        BtnKeluar= (Button) findViewById(R.id.keluar);

        BtnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(Profile.this, Menu.class);
                startActivity(inten);
            }
        });
    }
}
