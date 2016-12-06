package com.codekul.statusbarnotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_SIMPLE = 1234;
    private static final int REQ_NOTIFICATION = 4569;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOkay).setOnClickListener(this::clicked);
    }

    private void clicked(View view) {
        if(view.getId() == R.id.btnOkay) showNotification();
    }

    private void showNotification() {

        Intent intent = new Intent(this, NotificationActivity.class);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, REQ_NOTIFICATION, intent, PendingIntent.FLAG_ONE_SHOT);

        //new code
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_android_black_24dp,"Action",pendingIntent)
                        .build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentInfo(getResources().getString(R.string.contentInfo));
        builder.setContentTitle(getResources().getString(R.string.contentTitle))
                .setContentText(getResources().getString(R.string.contentText))
                .setDefaults(Notification.DEFAULT_ALL)
//                .setOngoing(true)
                .setContentIntent(pendingIntent)
                //.addAction(R.mipmap.ic_launcher, getResources().getString(R.id.action), pendingIntent)
                .addAction(action)
                .setAutoCancel(true);


        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_SIMPLE, notification);

    }
}
