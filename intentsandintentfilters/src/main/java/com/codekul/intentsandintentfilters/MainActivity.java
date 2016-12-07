package com.codekul.intentsandintentfilters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOkay).setOnClickListener(this::click);
    }

    private void click(View view) {
        openImage();
    }

    private void openComman(){
        Intent intent = new Intent();
        intent.setAction("com.codekul.action.COMMAN");
        startActivity(intent);
    }

    private void openAllApps() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(intent);
    }

    private void dial(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        startActivity(intent);
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel://9762548833"));
        startActivity(intent);
    }

    private void openCommanWithData() {
        Intent intent = new Intent();
        intent.setAction("com.codekul.action.COMMAN");
        /* # */intent.setData(Uri.parse("http://codekul.com"));
        startActivity(intent);
    }

    private void openLink(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        /* # */intent.setData(Uri.parse("http://codekul.com"));
        startActivity(intent);
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("my.png"),"image/*");
        startActivity(intent);
    }
}
