package com.codekul.uixml;

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

        TextView text = (TextView) findViewById(R.id.textStatus);

        Button btnOkay = (Button) findViewById(R.id.btnOkay);
        btnOkay.setOnClickListener(new MyClick());
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("ano inner type");
            }
        });

        btnOkay.setOnClickListener(view -> {
            text.setText("lambda");
        }); // lambda

        btnOkay.setOnClickListener(this::okayClicked); // method reference

    }

    private void okayClicked(View view){
        TextView text = (TextView) findViewById(R.id.textStatus);
        text.setText("method ref");
    }

    private class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }
}
