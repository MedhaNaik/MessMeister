package com.example.android.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class first_page extends AppCompatActivity {

    public SQLiteDatabase db = null;
    public SQLiteOpenHelper LoginDatabaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginDatabaseHelper = new LoginDatabaseHelper(this, "LOGIN_DB", null, 1);
        try {
            db = LoginDatabaseHelper.getWritableDatabase();
            Cursor cursor = db.query("login", new String[]{"name", "password"}, null, null, null, null, null);
            cursor.moveToFirst();
            if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
                Log.i("error", "hh");
                Intent intent = new Intent(this, Signup.class);
                startActivity(intent);
                finish();
            } else {
                Log.i("ji", "" + cursor.getCount());
                cursor.close();
                db.close();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        } catch (Exception e) {
            Log.e("error", "tt");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
