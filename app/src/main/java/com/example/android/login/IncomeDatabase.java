package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by avhirup on 11/16/2015.
 */
public class IncomeDatabase {

    LoginDatabaseHelper loginDatabaseHelper;
    SQLiteDatabase db;

    public IncomeDatabase(Context context) {

        loginDatabaseHelper = new LoginDatabaseHelper(context, "LOGIN_DB", null, 1);
        db = loginDatabaseHelper.getWritableDatabase();

    }

    public void add(Income income) {
        ContentValues value = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        Log.e("date", date);
        value.put(loginDatabaseHelper.Income_date, date);
        value.put(loginDatabaseHelper.Income_tag, income.getIncomeName());
        value.put(loginDatabaseHelper.Income_amount, income.getAmount());


        db.insert(loginDatabaseHelper.TABLE_Income, null, value);

    }

    public Cursor getIncome() {
        String query = "select  * from " + LoginDatabaseHelper.TABLE_Income + " ;";
        return db.rawQuery(query, null);

    }

    public ArrayList<String> getMonth()

    {

        ArrayList<String> yearlist = new ArrayList<String>();
        String query = "select _date from Income";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String date = cursor.getString(0);
            Log.e("year", date);
            String q = "select strftime(\"%Y-%m\", \"" + date + "\");";
            Cursor cursor1 = db.rawQuery(q, null);
            if (cursor1 != null) {
                cursor1.moveToFirst();
                date = cursor1.getString(0);
                cursor1.close();
            }
            db.execSQL("insert into months values(\"" + date + "\");");
            cursor.moveToNext();

        }
        Cursor cursor3 = db.rawQuery("select distinct  _id from months order by _id desc;", null);
        if (cursor3 != null) {
            cursor3.moveToFirst();
            while (!cursor3.isAfterLast()) {
                yearlist.add(cursor3.getString(0));
                cursor3.moveToNext();
            }
            cursor3.close();
        }
        return yearlist;

    }

    public ArrayList<String> getYear() {
        ArrayList<String> yearlist = new ArrayList<String>();
        String query = "select _date from Income";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String date = cursor.getString(0);
            Log.e("year", date);
            String q = "select strftime(\"%Y\", \"" + date + "\");";
            Cursor cursor1 = db.rawQuery(q, null);
            if (cursor1 != null) {
                cursor1.moveToFirst();
                date = cursor1.getString(0);
                cursor1.close();
            }
            db.execSQL("insert into years values(\"" + date + "\");");
            cursor.moveToNext();

        }
        Cursor cursor3 = db.rawQuery("select distinct _id from years order by _id  desc;", null);
        if (cursor3 != null) {
            cursor3.moveToFirst();
            while (!cursor3.isAfterLast()) {

                yearlist.add(cursor3.getString(0));
                cursor3.moveToNext();
            }
            cursor3.close();
        }
        return yearlist;


    }

    public String getTotal(String date) {

        String query = "select sum(m) from (Select _tag, sum(_amount) m from Income where _date between \"" + date + "-01\" " + " and  \"" + date + "-31\" group by _tag " + "union " + "Select _tag, sum(_amount) m from Expense where _date between \"" + date + "-01\" " + " and  \"" + date + "-31\" group by _tag); ";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return Integer.toString(cursor.getInt(0));

    }

    public ArrayList<String> getAllIncomes() {
        ArrayList<String> memberlist = new ArrayList<String>();
        String query = "select distinct _tag from " + loginDatabaseHelper.TABLE_Income + ";";
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

    public List<pair> getTuples(String date) {
        List<pair> array_list = new ArrayList<>();
        Log.e("date", date);
        // String query = "select _tag, _amount from Income where _date between \"2015-11-01\" and \"2015-11-31\";";
        String query = "Select _tag, sum(_amount) from Income where _date between \"" + date + "-01\" " + " and  \"" + date + "-31\" group by _tag " + "union " + "Select _tag, sum(_amount) from Expense where _date between \"" + date + "-01\" " + " and  \"" + date + "-31\" group by _tag; ";
        Cursor cursor = db.rawQuery(query, null);
        Log.e("columns", cursor.getColumnCount() + "");
        Log.e("rows", cursor.getCount() + "");
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                array_list.add(new pair(cursor.getString(0), cursor.getInt(1)));
                cursor.moveToNext();
            }

            cursor.close();
        }
        Log.e("size of array", array_list.size() + "");
        return array_list;
    }


    public List<pair> getToday(String date) {
        List<pair> array_list = new ArrayList<>();
        Log.e("date", date);
        // String query = "select _tag, _amount from Income where _date between \"2015-11-01\" and \"2015-11-31\";";
        String query = "Select _tag, sum(_amount) from Income where _date = \"" + date + "\" group by _tag " + "union " + "Select _tag, sum(_amount) from Expense where _date = \"" + date + "\"  group by _tag; ";
        Cursor cursor = db.rawQuery(query, null);
        Log.e("columns", cursor.getColumnCount() + "");
        Log.e("rows", cursor.getCount() + "");
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                array_list.add(new pair(cursor.getString(0), cursor.getInt(1)));
                cursor.moveToNext();
            }

            cursor.close();
        }
        Log.e("size of array", array_list.size() + "");
        return array_list;
    }

}






