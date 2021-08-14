package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by medha on 27/10/15.
 */
public class RateDataBase {

    LoginDatabaseHelper loginDatabaseHelper;
    SQLiteDatabase db;

    public RateDataBase(Context context) {
        loginDatabaseHelper = new LoginDatabaseHelper(context, "LOGIN_DB", null, 1);
        db = loginDatabaseHelper.getWritableDatabase();
    }


    public void add(Rate rate) {
        Log.e("helper", "in add");
        ContentValues values = new ContentValues();
        values.put(loginDatabaseHelper.Rate_category, rate.getCategory());
        values.put(loginDatabaseHelper.Rate_amount, rate.getAmount());

        db.insert(loginDatabaseHelper.TABLE_Rate, null, values);

    }


    public boolean delete(Integer rateId) {
        boolean check;
        try {
            String query = " delete from" + loginDatabaseHelper.TABLE_Rate + " where " + loginDatabaseHelper.Rate_rate_id + " = " + rateId;
            db.execSQL(query);
            check = true;
        } catch (Exception e) {
            check = false;
        }
        return check;
    }


    public Boolean edit(Rate rate) {
        boolean check;
        try {
            String query = " update " + loginDatabaseHelper.TABLE_Rate +
                    " set " +
                    loginDatabaseHelper.Rate_category + " = " + "\"" + rate.getCategory() + "\"," +
                    loginDatabaseHelper.Rate_amount + " = " + rate.getAmount() +
                    " where " + loginDatabaseHelper.Rate_rate_id + " = " + rate.getRate_id() + ";";
            db.execSQL(query);
            check = true;
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    public Cursor getAmount(String category) {
        String query = "select " + loginDatabaseHelper.Rate_amount + " from " + loginDatabaseHelper.TABLE_Rate +
                " where " + loginDatabaseHelper.TABLE_Rate + "." + loginDatabaseHelper.Rate_category + " = " + category + " ;";

        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public int getAmount(int rate_id) {
        String query = "select " + loginDatabaseHelper.Rate_amount + " from " + loginDatabaseHelper.TABLE_Rate + " where "
                + loginDatabaseHelper.Rate_rate_id + " = " + rate_id + " ;";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return Integer.parseInt(cursor.getString(0));

    }

    public Cursor getRateTable() {
        String query = "select * from " + loginDatabaseHelper.TABLE_Rate + " ;";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public int getamt(int rate_id) {
        String query = "select * from " + loginDatabaseHelper.TABLE_Rate +
                " where " + loginDatabaseHelper.Rate_rate_id + "=" + rate_id + ";";
        Cursor cursor = db.rawQuery(query, null);


        int a = 0;
        if (cursor == null)
            Log.e("he", "in array cursor null");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            a = cursor.getInt(2);

            //Log.e("Member table",tuple);
            cursor.moveToNext();
        }
        return a;
    }

    public int getrateId(String category) {
        String query = "select * from " + loginDatabaseHelper.TABLE_Rate +
                " where " + loginDatabaseHelper.Rate_category + " = " + "\"" + category + "\"" + ";";
        Cursor cursor = db.rawQuery(query, null);


        int a = 0;
        if (cursor == null)
            Log.e("he", "in array cursor null");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            a = cursor.getInt(0);

            //Log.e("Member table",tuple);
            cursor.moveToNext();
        }
        return a;
    }

    public ArrayList<String> getcategoryNames() {
        ArrayList<String> grouplist = new ArrayList<String>();
        String query = " select * from " + loginDatabaseHelper.TABLE_Rate + ";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor == null)
            Log.e("he", "in array cursor null");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            grouplist.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return grouplist;
    }

    public String getCategory(int id) {
        String query = "select * from " + loginDatabaseHelper.TABLE_Rate +
                " where " + loginDatabaseHelper.Rate_rate_id + " = " + id + ";";
        Cursor cursor = db.rawQuery(query, null);


        String a = null;
        if (cursor == null)
            Log.e("he", "in array cursor null");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            a = cursor.getString(1);

            //Log.e("Member table",tuple);
            cursor.moveToNext();
        }
        cursor.close();
        return a;
    }
}
