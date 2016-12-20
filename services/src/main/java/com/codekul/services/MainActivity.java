
package com.codekul.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btnOkay).setOnClickListener(this::click);
        findViewById(R.id.btnStop).setOnClickListener(this::click);
    }

    private void click(View view) {
        if(view.getId() == R.id.btnOkay) startIntentService();
    }

    private void startSimple() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private void stopSimple() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private void startIntentService() {

        Intent intent = new Intent(this,MyIntentService.class);
        startService(intent);
    }
}
