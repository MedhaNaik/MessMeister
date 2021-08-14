package com.example.android.login;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MonthlyUpdateService extends IntentService {

    MemberDatabase memberDatabase;
    NotificationDatabase notificationDatabase;
    RateDataBase rateDataBase;

    public MonthlyUpdateService() {
        super("MonthlyUpdate");
        memberDatabase = new MemberDatabase(this);
        notificationDatabase = new NotificationDatabase(this);
        rateDataBase = new RateDataBase(this);
    }

    protected void onHandleIntent(Intent intent) {
        Cursor cursor = memberDatabase.getMember();
        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            String start_of_month = cursor.getString(3);
            String haspaid = cursor.getString(7);
            int m_id = Integer.parseInt(cursor.getString(0));
            int r_id = Integer.parseInt(cursor.getString(5));
            int olddueAmount = Integer.parseInt(cursor.getString(6));
            int amount = rateDataBase.getAmount(r_id);
            int newAmount = olddueAmount + amount;


            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String today = formatter.format(Calendar.getInstance().getTime());
            String end_of_month = incrementNotifyOn(start_of_month);
            try {
                Date todayDate = formatter.parse(today, null);
                Date end_of_monthDate = formatter.parse(end_of_month, null);

                if (todayDate.before(end_of_monthDate)) {
                    Notification notification = new Notification(0, m_id, today);
                    notificationDatabase.add(notification);

                    memberDatabase.changeDueAmount(m_id, newAmount);
                }

            } catch (Exception e) {
                Log.e("exception", "Exceoption");
            }


        }

    }

    public String incrementNotifyOn(String date) {
        String dt = date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (Exception e) {
        }
        c.add(Calendar.DATE, 30);  // number of days to add
        dt = sdf.format(c.getTime());  // dt is now the new date
        Log.e("helper", dt);
        return dt;
    }

}
