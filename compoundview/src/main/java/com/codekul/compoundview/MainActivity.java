package com.codekul.compoundview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layoutRoot = (LinearLayout) findViewById(R.id.layoutRoot);

        layoutRoot.addView(giveMeSocialCard("using touch gestures that loosely correspond to real-world actions, such as swiping, tapping and pinching, to manipulate on-screen objects, along with a virtual keyboard for text input"));;

        layoutRoot.addView(giveMeSocialCard(" In addition to touchscreen devices, Google has further developed Android TV for televisions, Android Auto for cars, and Android Wear for wrist watches, each with a specialized user interface."));;

        layoutRoot.addView(giveMeSocialCard("Variants of Android are also used on notebooks, game consoles, digital cameras, and other electronics."));;

    }

    private View giveMeSocialCard(String status){

        LinearLayout layoutRoot = (LinearLayout) findViewById(R.id.layoutRoot);

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);

        View compundView = inflater.inflate(R.layout.social_media, null, false);
        TextView textStatus = (TextView) compundView.findViewById(R.id.textStatus);
        textStatus.setText(status);

        return compundView;
    }
}
