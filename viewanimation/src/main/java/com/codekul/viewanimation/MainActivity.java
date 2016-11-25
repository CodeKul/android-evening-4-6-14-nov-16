package com.codekul.viewanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btnMixed).setOnClickListener(this::clicked);
        findViewById(R.id.btnRotate).setOnClickListener(this::clicked);
        findViewById(R.id.btnScale).setOnClickListener(this::clicked);
    }

    private void clicked(View view) {
        if(view.getId() == R.id.btnMixed) animateRocket(R.anim.mixed);
        if(view.getId() == R.id.btnRotate) animateRocket(R.anim.rotate);
        if(view.getId() == R.id.btnScale) animateRocket(R.anim.scale);
    }

    private void animateRocket(int animation){
        findViewById(R.id.imageAnimation)
                .startAnimation(AnimationUtils.loadAnimation(this,animation));
    }
}
