package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by manjush on 10-11-2015.
 */
public class MessMemberGroupDatabase {

    LoginDatabaseHelper loginDatabaseHelper;
    SQLiteDatabase db;

    public MessMemberGroupDatabase(Context context) {

        loginDatabaseHelper = new LoginDatabaseHelper(context, "LOGIN_DB", null, 1);
        db = loginDatabaseHelper.getWritableDatabase();

    }

    public void add(MessMemberGroup m) {
        ContentValues value = new ContentValues();
        value.put(loginDatabaseHelper.MessMember_Group_messmember_id, m.getMid());
        value.put(loginDatabaseHelper.MessMember_Group_group_id, m.getGid());
        db.insert(loginDatabaseHelper.TABLE_MessMember_Group, null, value);

    }

    public Cursor getMemberGroupTable() {
        String query = "select * from " + loginDatabaseHelper.TABLE_MessMember_Group + ";";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }


    public ArrayList<Integer> getgrpids(int mid) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        Cursor cursor;


        String query = " select * from " + loginDatabaseHelper.TABLE_MessMember_Group +
                " where " + loginDatabaseHelper.MessMember_Group_messmember_id + " = " + mid + ";";

        cursor = db.rawQuery(query, null);
        if (cursor == null)

            Log.e("he", "in array cursor null");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            a.add(cursor.getInt(1));
            cursor.moveToNext();
        }

        cursor.close();
        return a;

    }


    public ArrayList<Integer> getmidlist(int grpid) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        Cursor cursor;


        String query = " select * from " + loginDatabaseHelper.TABLE_MessMember_Group +
                " where " + loginDatabaseHelper.MessMember_Group_group_id + " = " + grpid + ";";

        cursor = db.rawQuery(query, null);
        if (cursor == null)

            Log.e("he", "in array cursor null");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            a.add(cursor.getInt(0));
            cursor.moveToNext();
        }

        cursor.close();
        return a;

    }

    public void delete(int mid) {
        String query = " delete from " + loginDatabaseHelper.TABLE_MessMember_Group +
                " where " + loginDatabaseHelper.MessMember_Group_messmember_id + " = " + mid + ";";
        db.execSQL(query);
    }


    public void delete(int mid, int grpid) {
        Log.e("hl", mid + " " + grpid);
        String query = " delete from " + loginDatabaseHelper.TABLE_MessMember_Group +
                " where " + loginDatabaseHelper.MessMember_Group_messmember_id + " = " + mid + " and " +
                loginDatabaseHelper.MessMember_Group_group_id + " = " + grpid + ";";
        db.execSQL(query);
    }

    public void deletebyGrp(int grpId) {
        String query = " delete from " + loginDatabaseHelper.TABLE_MessMember_Group +
                " where " + loginDatabaseHelper.MessMember_Group_group_id + " = " + grpId + ";";
        db.execSQL(query);
    }


}
