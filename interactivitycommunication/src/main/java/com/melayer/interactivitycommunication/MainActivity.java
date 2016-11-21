package com.melayer.interactivitycommunication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRbs();

        findViewById(R.id.btnNew).setOnClickListener(this::clicked);
    }

    private void clicked(View view) {
        if(view.getId() == R.id.btnNew) openNewActivity();
    }

    private void openNewActivity() {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

    private void initRbs() {
        ((RadioButton)findViewById(R.id.rbAndroid))
                .setOnCheckedChangeListener(this::checkChanged);

        ((RadioButton)findViewById(R.id.rbApple))
                .setOnCheckedChangeListener(this::checkChanged);

        /*((RadioButton)findViewById(R.id.rbRim))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    }
                });

        ((RadioButton)findViewById(R.id.rbRim))
                .setOnCheckedChangeListener((buttonView, isChecked) -> {

                });*/

        ((RadioButton)findViewById(R.id.rbRim))
                .setOnCheckedChangeListener(this::checkChanged);
    }

    private void checkChanged(CompoundButton compoundButton, boolean isChecked) {
        if(isChecked) {
            if (compoundButton instanceof RadioButton) {
                RadioButton rb = (RadioButton) compoundButton;
                setOs(rb.getText().toString());
            }
        }
    }

    private void setOs(String os){
        ((EditText)findViewById(R.id.edtOs)).setText(os);
    }
}
