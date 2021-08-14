package com.example.android.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SnoozeReceiver extends BroadcastReceiver {
    private final static int PERIOD = 7;
    NotificationDatabase notificationDatabase;

    public SnoozeReceiver() {
    }

    public String incrementNotifyOn(String date) {
        String memberNotifyOn = " ";
        String dt = date;  // notifyDate
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (Exception e) {
        }
        c.add(Calendar.DATE, PERIOD);  // number of days to add
        dt = sdf.format(c.getTime());  // dt is now the new date
        Log.e("helper", dt);
        return dt;
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        int member_id = intent.getExtras().getInt("memberid");
        //Log.e("helper",member_id+"");
        notificationDatabase = new NotificationDatabase(context);
        int notification_id = notificationDatabase.getNotificationId(member_id);//i needmethod to mid -> nid
        String notifyOn = notificationDatabase.getNotifyOn(member_id);
        Log.e("helper", notifyOn + "");
        String newNotifyOn = incrementNotifyOn(notifyOn);

        Notification notification = new Notification(notification_id, member_id, newNotifyOn);
        notificationDatabase.updateNotifyOn(notification);
        Toast.makeText(context, "To be next notified on" + newNotifyOn, Toast.LENGTH_LONG).show();
    }
}
