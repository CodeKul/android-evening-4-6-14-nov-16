package com.codekul.buttonclicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGood = (Button) findViewById(R.id.btnGood);
        btnGood.setOnClickListener(this::goodClick);

        Button btnBetter = (Button) findViewById(R.id.btnBetter);
        btnBetter.setOnClickListener(this::betterClick);

        Button btnBest = (Button) findViewById(R.id.btnBest);
        btnBest.setOnClickListener(this::bestClick);
    }

    private void goodClick(View view){
        TextView textInfo = (TextView) findViewById(R.id.textInfo);
        textInfo.setText("Good Clicked");
    }

    private void betterClick(View view) {
        TextView textInfo = (TextView) findViewById(R.id.textInfo);
        textInfo.setText("Better Clicked");
    }

    private void bestClick(View view) {
        TextView textInfo = (TextView) findViewById(R.id.textInfo);
        textInfo.setText("Best Clicked");
    }
}
