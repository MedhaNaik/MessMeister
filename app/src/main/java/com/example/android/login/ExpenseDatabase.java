package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by avhirup on 11/16/2015.
 */
public class ExpenseDatabase {
    LoginDatabaseHelper loginDatabaseHelper;
    SQLiteDatabase db;

    public ExpenseDatabase(Context context) {

        loginDatabaseHelper = new LoginDatabaseHelper(context, "LOGIN_DB", null, 1);
        db = loginDatabaseHelper.getWritableDatabase();

    }

    public void add(Expense expense) {
        ContentValues value = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        String query = "insert into " + LoginDatabaseHelper.TABLE_Expense +
                " values ( \"" + date + "\" , \"" + expense.getExpName() + "\" , " + expense.getAmount() + " ) ; ";
        Log.e("helper", query);
        db.execSQL(query);

    }

    public Cursor getExpense() {
        String query = "select * from " + LoginDatabaseHelper.TABLE_Expense + " ;";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public ArrayList<String> getAllExpenses() {
        ArrayList<String> memberlist = new ArrayList<String>();
        String query = "select distinct _tag from " + loginDatabaseHelper.TABLE_Expense + ";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor == null)
            Log.e("he", "in array cursor null");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            memberlist.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return memberlist;
    }
}
