package com.codekul.androidapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity mainActivity = this;
        Context context = mainActivity;
        context = this;

        LinearLayout.LayoutParams paramRoot
                = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setLayoutParams(paramRoot);
        setContentView(rootLayout);

        LinearLayout.LayoutParams paramsText =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        final TextView text = new TextView(this);
        text.setLayoutParams(paramsText);
        text.setText("I am TextView");

        LinearLayout.LayoutParams paramsBtn =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button btnOkay = new Button(this);
        btnOkay.setLayoutParams(paramsBtn);
        btnOkay.setText("Okay");
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("Clicked");
            }
        });

        rootLayout.addView(btnOkay);

        rootLayout.addView(text);
    }

    private class Click implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }
}
