package com.example.uasmobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    Button button;
    private SharedPreference sp;
    Activity context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = new SharedPreference();
        button=(Button)findViewById(R.id.login);

        username =(EditText)findViewById(R.id.txtusername);
        password =(EditText)findViewById(R.id.txtpassword);

    }



    public void checklogin(View v) {
        final String email = username.getText().toString();
        switch (v.getId()) {
            case R.id.login:
                hideKeybaord(v);
                break;
        }
        if (!isValidEmail(email)) {
            username.setError("Username yang anda masukan salah!");
        }
        final String pass = password.getText().toString();
        if (!isValidPassword(pass)) {
            password.setError("Password yang anda masukan salah!");
        }
        if (isValidEmail(email) && isValidPassword(pass)) {
            sp.save(context, "username", email);
            Toast.makeText(getApplicationContext(), "Berhasil login!",Toast.LENGTH_LONG).show();
            Intent ExplicitIntent=new Intent(MainActivity.this, MenuActivity.class);
            startActivity(ExplicitIntent);
        }
    }
    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }

    public boolean isValidPassword (String password) {
        if (password != null && password.equals("18101056")) {
            return true;
        }
        return false;
    }

    public boolean isValidEmail(String username) {
        if (username != null && username.equals("18101056")) {
            return true;
        }
        return false;
    }
}
