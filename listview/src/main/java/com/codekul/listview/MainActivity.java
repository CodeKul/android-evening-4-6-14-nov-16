package com.codekul.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> dataSet = new ArrayList<>();
        dataSet.add("India");
        dataSet.add("China");
        dataSet.add("Japan");
        dataSet.add("USA");
        dataSet.add("AUS");
        dataSet.add("UK");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataSet);

        ListView listView = (ListView) findViewById(R.id.listCountries);
        listView.setAdapter(adapter);
    }
}
