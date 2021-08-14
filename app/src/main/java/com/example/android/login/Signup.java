package com.example.android.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    FloatingActionButton button;
    LoginDatabaseHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar m_toolbar = (Toolbar) findViewById(R.id.t_bar);
        setSupportActionBar(m_toolbar);
        button = (FloatingActionButton) findViewById(R.id.fabBtn);
        button.hide();
        final EditText myEditText = (EditText) findViewById(R.id.Rpassword);
        final EditText rpass = (EditText) findViewById(R.id.password);
        final EditText name = (EditText) findViewById(R.id.username);
        myEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                button = (FloatingActionButton) findViewById(R.id.fabBtn);
                if (hasFocus) {
                    button.show();
                } else if (myEditText.getText().toString().equals("") || rpass.getText().toString().equals("") || name.getText().toString().equals(""))

                {
                    button.hide();
                }
            }
        });
        setupUI(findViewById(R.id.parent));
        helper = new LoginDatabaseHelper(this, "LOGIN_DB", null, 1);
        db = helper.getWritableDatabase();
    }


    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    // button.hide();
                    EditText name = (EditText) findViewById(R.id.username);
                    EditText pass = (EditText) findViewById(R.id.password);
                    EditText rpass = (EditText) findViewById(R.id.Rpassword);
                    name.clearFocus();
                    pass.clearFocus();
                    rpass.clearFocus();
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }

    public void signup(View view) {
        final EditText myEditText = (EditText) findViewById(R.id.Rpassword);
        final EditText rpass = (EditText) findViewById(R.id.password);
        final EditText name = (EditText) findViewById(R.id.username);
        String name1 = name.getText().toString();
        String rpass1 = rpass.getText().toString();
        String pass2 = myEditText.getText().toString();
        if (rpass1.equals(pass2)) {
            ContentValues user = new ContentValues();
            user.put("name", name1);
            user.put("password", pass2);
            db.insert("login", null, user);
            Intent intent = new Intent(this, homepage.class);
            intent.putExtra("value", 0);
            startActivity(intent);
            db.close();
            finish();
        } else {
            Toast.makeText(this, "Passwords Did Not Match!", Toast.LENGTH_SHORT).show();
        }

    }

}
