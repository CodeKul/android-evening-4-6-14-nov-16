package com.melayer.interactivitycommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class NewActivity extends AppCompatActivity {

    public static final String KEY_MY_MOBILE = "myMobile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        setOs(getOsFromMain());

        findViewById(R.id.btnBack)
                .setOnClickListener(this::click);
    }

    private void click(View view) {
        if(view.getId() == R.id.btnBack) throwDataToBack();
    }

    private void throwDataToBack() {
        Intent intent = new Intent();
        intent.putExtra(KEY_MY_MOBILE,getMobile());

        setResult(RESULT_OK,intent);
        finish();
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

    private String getMobile(){
        return  ((EditText)findViewById(R.id.edtMobile)).getText().toString();
    }
}
