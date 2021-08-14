package com.example.android.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OnAlarmReceiver extends BroadcastReceiver {
    public OnAlarmReceiver() {
        Log.e("InOn Alarm ", "In consrtucutor");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, NotificationService.class));
    }
}
