package com.example.android.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by medha on 10/9/15.
 */
public class LoginDatabaseHelper extends SQLiteOpenHelper {


    public final static String TABLE_MessMember = "MessMember";
    public final static String TABLE_Rate = "Rate";
    public final static String TABLE_Group = "MemberGroup";
    public final static String TABLE_MessMember_Group = "MessMemberGroup";
    public final static String TABLE_Notification = "Notification";
    public final static String TABLE_Income = "Income";
    public final static String TABLE_Expense = "Expense ";
    public final String MessMember_member_id = "_id";
    public final String MessMember_name = "_name";
    public final String MessMember_start_date = "start_date";
    public final String MessMember_startof_month = "startof_month";
    public final String MessMember_is_active = "is_active";
    public final String MessMember_rate_id = "rate_id";
    public final String MessMember_due_amt = "due_amt";
    public final String MessMember_has_paid = "has_paid";
    public final String MessMember_is_late = "is_late";
    public final String MessMember_phone = "phone";
    public final String MessMember_img_id = "img_id";
    public final String Rate_rate_id = "_id";
    public final String Rate_category = "category";
    public final String Rate_amount = "amount";
    public final String Group_groupid = "_id";
    public final String Group_groupName = "group_name";
    public final String MessMember_Group_messmember_id = "member_id";
    public final String MessMember_Group_group_id = "group_id";
    public final String Notification_id = "_nid";
    public final String Notification_mid = "_mid";
    public final String Notification_notifyOn = "notifyOn";
    public final String Income_date = "_date";
    public final String Income_tag = "_tag";
    public final String Income_amount = "_amount";
    public final String Expense_date = "_date";
    public final String Expense_tag = "_tag";
    public final String Expense_amount = "_amount";

    public LoginDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {


        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        ///write your tables here - medha

        db.execSQL("create table login(_id integer primary key autoincrement, " +
                "name text, password text);");

        String query1 = " create table " + TABLE_Rate +
                " ( " +
                Rate_rate_id + " INTEGER primary key AUTOINCREMENT, " +
                Rate_category + " TEXT, " +
                Rate_amount + " INTEGER " +
                " ); ";

        db.execSQL(query1);

        String query3 = " create table " + TABLE_Group +
                " ( " +
                Group_groupid + " INTEGER primary key AUTOINCREMENT, " +
                Group_groupName + " TEXT  " +
                " ); ";
        db.execSQL(query3);


        String query2 = " create table " + TABLE_MessMember +
                " ( " +
                MessMember_member_id + " INTEGER primary key AUTOINCREMENT, " +
                MessMember_name + " TEXT UNIQUE not NULL, " +
                MessMember_start_date + " TEXT, " +
                MessMember_startof_month + " TEXT, " +
                MessMember_is_active + " INTEGER, " +
                MessMember_rate_id + " INTEGER, " +
                MessMember_due_amt + " INTEGER, " +
                MessMember_has_paid + " BOOLEAN, " +
                MessMember_is_late + " INTEGER, " +
                MessMember_phone + " Text, " +
                MessMember_img_id + " INTEGER, " +
                " foreign key " + "( " + MessMember_rate_id + " )" + " references " + TABLE_Rate +
                " ); ";

        db.execSQL(query2);


        String query7 = " create table " + TABLE_MessMember_Group + " ( " +
                MessMember_Group_messmember_id + " INTEGER, " +
                MessMember_Group_group_id + " INTEGER, " +
                " foreign key " + "(" + MessMember_Group_messmember_id + ")" + " references " + TABLE_MessMember + "(" + MessMember_member_id + ")" + " , " +
                " foreign key " + "(" + MessMember_Group_group_id + ")" + " references " + TABLE_Group + "(" + Group_groupid + ")" + ");";
        db.execSQL(query7);

        String query8 = " create table " + TABLE_Notification + " ( " +
                Notification_id + " INTEGER primary key AUTOINCREMENT, " +
                Notification_mid + " INTEGER, " +
                Notification_notifyOn + " TEXT, " +
                " foreign key " + "(" + Notification_mid + ")" + " references " + TABLE_MessMember + "(" + MessMember_member_id + ")" +
                ");";
        db.execSQL(query8);

        String query9 = "create table " + TABLE_Income + " ( " +
                Income_date + " TEXT , " +
                Income_tag + " TEXT , " +
                Income_amount + " INTEGER );";

        db.execSQL(query9);

        String query4 = "create table " + TABLE_Expense + " ( " +
                Expense_date + " TEXT , " +
                Expense_tag + " TEXT , " +
                Expense_amount + " INTEGER );";

        db.execSQL(query4);
        db.execSQL("create table months(_id text);");
        db.execSQL("create table years(_id text);");

     /*  String query10 = " insert into " + TABLE_Group +"(" +Group_groupName + ")"+ " values (\"Walchand \");";
        db.execSQL(query10);

        String query11 = " insert into " + TABLE_Rate +"(" + Rate_category + "")" +" values (\"boys monthly\",2400);";
        db.execSQL(query11);
        String query12 = " insert into " + TABLE_MessMember + " values (\"Avhirup\",\"2015-10-18\",\"2015-10-15\",1,1,0,0,1,\"8484008910\",0);";
        db.execSQL(query12);
        String query13 = " insert into " + TABLE_MessMember + " values (\"Rohan\",\"2015-10-15\",\"2015-10-10\",1,1,0,0,1,\"8275119400\",0);";
        db.execSQL(query13);
        String query14 = "insert into " + TABLE_MessMember_Group + " values (1,1);";
        db.execSQL(query14);
        String query15 = "insert into " + TABLE_MessMember_Group + " values (2,1);";
        db.execSQL(query15);*/

        ContentValues values = new ContentValues();
        values.put(Group_groupName, "Walchand");
        db.insert(TABLE_Group, null, values);

        ContentValues val = new ContentValues();
        val.put(Rate_category, "boys monthly");
        val.put(Rate_amount, 2400);
        db.insert(TABLE_Rate, null, val);

        ContentValues val1 = new ContentValues();
        val1.put(MessMember_name, "Avhirup");
        val1.put(MessMember_start_date, "2015-9-18");
        val1.put(MessMember_startof_month, "2015-9-15");
        val1.put(MessMember_is_active, 1);
        val1.put(MessMember_rate_id, 1);
        val1.put(MessMember_due_amt, 500);
        val1.put(MessMember_has_paid, 0);
        val1.put(MessMember_is_late, 1);
        val1.put(MessMember_phone, "8484008910");
        val1.put(MessMember_img_id, 0);
        db.insert(TABLE_MessMember, null, val1);

        ContentValues val2 = new ContentValues();
        val2.put(MessMember_name, "Rohan");
        val2.put(MessMember_start_date, "2015-9-15");
        val2.put(MessMember_startof_month, "2015-9-10");
        val2.put(MessMember_is_active, 1);
        val2.put(MessMember_rate_id, 1);
        val2.put(MessMember_due_amt, 800);
        val2.put(MessMember_has_paid, 0);
        val2.put(MessMember_is_late, 1);
        val2.put(MessMember_phone, "8275119400");
        val2.put(MessMember_img_id, 0);
        db.insert(TABLE_MessMember, null, val2);

        ContentValues val3 = new ContentValues();
        val3.put(MessMember_Group_messmember_id, 1);
        val3.put(MessMember_Group_group_id, 1);
        db.insert(TABLE_MessMember_Group, null, val3);

        ContentValues val4 = new ContentValues();
        val4.put(MessMember_Group_messmember_id, 2);
        val4.put(MessMember_Group_group_id, 1);
        db.insert(TABLE_MessMember_Group, null, val4);

        ContentValues val5 = new ContentValues();
        val5.put(Notification_mid, 1);
        val5.put(Notification_notifyOn, "2015-11-20");
        db.insert(TABLE_Notification, null, val5);

        ContentValues val6 = new ContentValues();
        val6.put(Notification_mid, 2);
        val6.put(Notification_notifyOn, "2015-11-20");
        db.insert(TABLE_Notification, null, val6);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

