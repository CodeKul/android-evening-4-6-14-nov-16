package com.codekul.ownhandling;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ((ImageView)findViewById(R.id.imageCenter)).setImageResource(R.drawable.hulk);
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ((ImageView)findViewById(R.id.imageCenter)).setImageResource(R.drawable.hulk1);
        }
        else {
            ((ImageView)findViewById(R.id.imageCenter)).setImageResource(R.mipmap.ic_launcher);
        }
    }
}
