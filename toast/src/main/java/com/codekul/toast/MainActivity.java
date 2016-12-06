package com.codekul.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOkay).setOnClickListener(this::clicked);
    }

    private void clicked(View view) {
        if(view.getId() == R.id.btnOkay) customToast();
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void customToast(){
        Toast toast = Toast.makeText(this,"",Toast.LENGTH_SHORT);
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.ic_launcher);
        toast.setView(image);
        toast.show();
    }
}
