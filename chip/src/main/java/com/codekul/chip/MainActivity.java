package com.codekul.chip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

public class MainActivity extends AppCompatActivity {

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflater = LayoutInflater.from(this);
        findViewById(R.id.btnAdd).setOnClickListener(v -> createChip((FlexboxLayout) findViewById(R.id.flexBox)));
    }

    private void createChip(FlexboxLayout layout) {

        LinearLayout layoutChip = (LinearLayout) inflater.inflate(R.layout.chip,null,false);
        ((TextView)layoutChip.findViewById(R.id.textChip)).setText(String.valueOf(layout.getChildCount()));
        layoutChip.findViewById(R.id.imgRemove).setOnClickListener(v -> layout.removeView(layoutChip));

        layout.addView(layoutChip);
    }
}
