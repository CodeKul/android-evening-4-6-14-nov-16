package com.codekul.simplevalueresources;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String nameBtn = getResources().getString(R.string.btnOkay);
        Log.i("@codekul","Name of Button - "+nameBtn);

        int pureWhite = getResources().getColor(R.color.pureWhite);
        pureWhite = ContextCompat.getColor(this,R.color.pureWhite);
    }
}
