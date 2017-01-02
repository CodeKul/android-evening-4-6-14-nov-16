package com.codekul.telephony;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TelephonyManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        findViewById(R.id.btnCellInfo).setOnClickListener(this::cellInfo);
        findViewById(R.id.btnSendSms).setOnClickListener(this::sendSms);
    }

    private void sendSms(View view) {

        Intent intentSent = new Intent("com.codekul.SMS_SENT");
        PendingIntent pSent = PendingIntent.getBroadcast(this,1506,intentSent,PendingIntent.FLAG_ONE_SHOT);

        Intent intentDelivered = new Intent("com.codekul.SMS_DELIVERED");
        PendingIntent pDelivered = PendingIntent.getBroadcast(this,1506,intentDelivered,PendingIntent.FLAG_ONE_SHOT);

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage("+919960448507",null,"Hello My self",pSent, pDelivered);
    }

    private void cellInfo(View view) {
        Log.i("@codekul"," Operator - "+manager.getSimOperator());
        Log.i("@codekul"," IMEI - "+manager.getDeviceId());
        Log.i("@codekul"," ISO - "+manager.getSimCountryIso());
        Log.i("@codekul"," Nw Name - "+manager.getNetworkOperatorName());
        Log.i("@codekul"," Mobile Num - "+manager.getLine1Number());
    }
}
