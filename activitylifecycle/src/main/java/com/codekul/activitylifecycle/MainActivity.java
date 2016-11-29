package com.codekul.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int i = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i = 12;
        mt("onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        mt("onRestart "+ i);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mt("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        mt("onResume");
    }

    @Override
    protected void onPause() {
        mt("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        mt("onStop");
        i = 99;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mt("onDestroy");
        super.onDestroy();
    }

    private void mt(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
