package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by manjush on 12-11-2015.
 */
public class NotificationDatabase {

    LoginDatabaseHelper loginDatabaseHelper;
    SQLiteDatabase db;

    public NotificationDatabase(Context context) {
        loginDatabaseHelper = new LoginDatabaseHelper(context, "LOGIN_DB", null, 1);
        db = loginDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();


    }


    public void add(Notification notification) {
        ContentValues values = new ContentValues();
        values.put(loginDatabaseHelper.Notification_mid, notification.getMember_id());
        values.put(loginDatabaseHelper.Notification_notifyOn, notification.getNotifyOn());

        db.insert(loginDatabaseHelper.TABLE_Notification, null, values);
    }


    public void updateNotifyOn(Notification notification) {
        String query = " update " + loginDatabaseHelper.TABLE_Notification +
                " set " +
                loginDatabaseHelper.Notification_notifyOn + " = " + "\"" + notification.getNotifyOn() + "\"" +
                " where " +
                loginDatabaseHelper.Notification_id + " = " + notification.getNotification_id() + " and " +
                loginDatabaseHelper.Notification_mid + " = " + notification.getMember_id() +
                ";";
        Log.e("Notification database", query);
        db.execSQL(query);
    }

    public void updateNotification(Notification notification) {
        String query = " update " + loginDatabaseHelper.TABLE_Notification +
                " set " +
                loginDatabaseHelper.Notification_notifyOn + " = " + "\"" + notification.getNotifyOn() + "\"" + "," +
                loginDatabaseHelper.Notification_id + " = " + notification.getNotification_id() + "," +
                loginDatabaseHelper.Notification_mid + " = " + notification.getMember_id() +
                ";";
        db.execSQL(query);

    }

    public void delete(int nid) {

        String query = " delete * from " + loginDatabaseHelper.TABLE_Notification +
                " where " +
                loginDatabaseHelper.Notification_id + " = " + nid +
                ";";

        db.execSQL(query);
    }

    public int getNotificationId(int mid) {
        String query = "select * from " + loginDatabaseHelper.TABLE_Notification +
                " where " + loginDatabaseHelper.Notification_mid + "=" + mid + ";";
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

    public int getMemberId(int nid) {
        String query = "select * from " + loginDatabaseHelper.TABLE_Notification +
                " where " + loginDatabaseHelper.Notification_id + "=" + nid + ";";
        Cursor cursor = db.rawQuery(query, null);


        int a = 0;
        if (cursor == null)
            Log.e("he", "in array cursor null");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            a = cursor.getInt(1);

            //Log.e("Member table",tuple);
            cursor.moveToNext();
        }
        return a;

    }


    public Cursor getNotificationTable() {
        String query = "select * from " + LoginDatabaseHelper.TABLE_Notification + " ;";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public String getNotifyOn(int memberid) {
        String query = "select * from " + loginDatabaseHelper.TABLE_Notification + " where _mid = " + memberid;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            String str = cursor.getString(2);
            cursor.close();
            return str;
        } else {
            Log.e("Error:", "No Values");
        }
        return "10-10-2010";
    }

}
