package com.example.android.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class WhatsappReceiver extends BroadcastReceiver {
    public WhatsappReceiver() {
        Log.e("Whatsapp", "whatsapp constructor");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "You are been informed about your due fees!\n Kindly bring fees today!\n ");
        sendIntent.setType("text/plain");
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.e("SMSWhatsapp", "Pressed YES");
        sendIntent.setPackage("com.whatsapp");
        context.startActivity(sendIntent);
        Log.e("SMSWhatsapp", "Completed");
    }
}
