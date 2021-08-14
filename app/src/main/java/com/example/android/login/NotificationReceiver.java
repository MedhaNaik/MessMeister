package com.example.android.login;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {
    final int PERIOD = 24 * 60 * 60 * 1000;

    public NotificationReceiver() {
        Log.e("helper", "IN NOTIFICATION RECEIVER");
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, OnAlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        Log.e("in Notifcation Receiver", "InSide OnReceive");
        mgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 15 * 60 * 1000, PERIOD, pi);

    }
}
