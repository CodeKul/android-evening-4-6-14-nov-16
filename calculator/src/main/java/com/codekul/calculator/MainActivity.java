package com.codekul.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String []nums = {"+","-","*","/","=","C","0","1","2","3","4","5","6","7","8","9"};
        CalcAdapter adapter = new CalcAdapter(this, nums, this::onDigitClick);
        ((GridView)findViewById(R.id.gridCalc)).setAdapter(adapter);

        Button btn = new Button(this);
        btn.setOnClickListener(v -> {});
    }

    private void onDigitClick(String digit) {
        ((EditText)findViewById(R.id.edtNum)).setText(digit);
    }
}
