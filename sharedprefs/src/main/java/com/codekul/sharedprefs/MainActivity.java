package com.codekul.sharedprefs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_SAMPLE_INT = "sampleInt";
    private static final String KEY_SAMPLE_BOOL = "sampleBool";
    private static final String KEY_SAMPLE_FLOAT = "sampleFloat";
    private static final String KEY_SAMPLE_LONG = "sampleLong";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSave).setOnClickListener(this::click);
        findViewById(R.id.btnShow).setOnClickListener(this::click);
    }

    private void click(View view) {
        if(view.getId() == R.id.btnSave) saveData();
        else readData();
    }

    private void saveData() {
        //SharedPreferences sharedPrefs = getPreferences(MODE_APPEND);
        SharedPreferences sharedPrefs = getSharedPreferences("my",MODE_APPEND);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(KEY_SAMPLE_INT,2017);
        editor.putLong(KEY_SAMPLE_LONG,System.currentTimeMillis());
        editor.putFloat(KEY_SAMPLE_FLOAT,25.89f);
        editor.putBoolean(KEY_SAMPLE_BOOL,true);
        editor.commit();
    }

    private void readData() {
        StringBuilder builder = new StringBuilder();

        SharedPreferences sharedPrefs = getSharedPreferences("my",MODE_APPEND);

        int sampleInt = sharedPrefs.getInt(KEY_SAMPLE_INT,-3);
        builder.append(sampleInt).append(", ");

        float sampleFloat = sharedPrefs.getFloat(KEY_SAMPLE_FLOAT,-3.9f);
        builder.append(sampleFloat).append(", ");

        boolean sampleBoolean = sharedPrefs.getBoolean(KEY_SAMPLE_BOOL,false);
        builder.append(sampleBoolean).append(", ");

        long sampleLong = sharedPrefs.getLong(KEY_SAMPLE_LONG,-1l);
        builder.append(sampleLong);

        ((TextView)findViewById(R.id.textData)).setText(builder.toString());
    }
}
