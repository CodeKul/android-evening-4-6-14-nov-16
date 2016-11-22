package com.melayer.interactivitycommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        setOs(getOsFromMain());
    }

    private String getOsFromMain() {
        Intent intent = getIntent();
        if(intent == null) throw new RuntimeException();

        Bundle bundle = intent.getExtras();
        String os = bundle.getString(MainActivity.KEY_MY_OS);

        return os;
    }

    private void setOs(String os) {
        ((TextView)findViewById(R.id.textOs)).setText(os);
    }
}
