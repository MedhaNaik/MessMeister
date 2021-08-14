package com.example.android.login;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;


public class NotificationService extends IntentService {
    private final static int PERIOD = 2 * 60 * 1000;
    //comment
    NotificationDatabase notificationDatabase = null;
    MemberDatabase memberDatabase;

    public NotificationService() {
        super("NotificationService");
        Log.e("In Notification Service", "Database Starting");
        Log.e("In Notification Service", "Database Started");

    }

    public void notificationGenerator() {

        notificationDatabase = new NotificationDatabase(this);
        memberDatabase = new MemberDatabase(this);

        Cursor cursor = notificationDatabase.getNotificationTable();  //modification made
        cursor.moveToFirst();
        Log.e("In Notification", "Notification Cursor");
        int j = 0;
        while (!cursor.isAfterLast()) {
            int memberid = Integer.parseInt(cursor.getString(1));
            String membername = memberDatabase.getMembername(memberid);
            int dueamount = memberDatabase.getdue_amt(memberid);


            NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
            notification.setAutoCancel(true);
            notification.setTicker("Late :" + membername);
            notification.setSmallIcon(R.drawable.businessman267);
            notification.setContentTitle("MEMBER: " + membername);
            notification.setContentText("Due Amount: " + dueamount);


            Intent intent = new Intent();
            intent.setAction("SNOOZE");
            intent.putExtra("memberid", memberid);
            PendingIntent snoozeIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.addAction(new NotificationCompat.Action(R.drawable.alarm16, "Snooze", snoozeIntent));


            Intent whatsapp = new Intent();
            whatsapp.setAction("WHATSAPPACTION");
            PendingIntent pendingwhatsappIntent = PendingIntent.getBroadcast(this, 12345, whatsapp, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.addAction(R.drawable.whatsapp16, "Whatsapp", pendingwhatsappIntent);

            notification.setAutoCancel(true);
            android.app.Notification n = notification.build();
            NotificationManagerCompat.from(this).notify(j, n);

            cursor.moveToNext();

            try {
                Thread.sleep(PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j++;
        }
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            notificationGenerator();
        } catch (Exception e) {
            Log.e("HELPER", e + "");
        }
    }


}
