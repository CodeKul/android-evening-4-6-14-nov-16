package com.codekul.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getBooleanExtra("state",false)) {
                //enabled
                changeAirPlane(R.drawable.ic_airplanemode_active_black_24dp);
            }
            else {
                //disabled
                changeAirPlane(R.drawable.ic_airplanemode_inactive_black_24dp);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFlightModeReceiver();

        findViewById(R.id.btnBroadCast).setOnClickListener(this::click);
    }

    private void click(View view) {
        if(view.getId() == R.id.btnBroadCast) {
            sendBroadcast(new Intent("com.codekul.action.CUSTOM_RECEIVER"));
        }
    }

    private void initFlightModeReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(receiver, filter);

    }

    private void changeAirPlane(int image) {
        ((ImageView)findViewById(R.id.imageAirPlane))
                .setImageResource(image);
    }
}
