package com.codekul.cutsomadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<MyItem> countries = new ArrayList<>();
        countries.add(new MyItem(1l,R.drawable.india,"India"));
        countries.add(new MyItem(2l,R.drawable.china,"China"));
        countries.add(new MyItem(3l,R.drawable.japan,"Japan"));
        countries.add(new MyItem(4l,R.drawable.russia,"Russia"));
        countries.add(new MyItem(5l,R.drawable.america,"America"));

        MyAdapter adapter = new MyAdapter(this, countries);

        ((ListView)findViewById(R.id.listCountries)).setAdapter(adapter);
    }
}
