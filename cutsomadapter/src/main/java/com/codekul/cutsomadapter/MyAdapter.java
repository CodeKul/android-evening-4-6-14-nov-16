package com.codekul.cutsomadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aniruddha on 28/11/16.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MyItem> countries;
    private LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<MyItem> countries) {
        this.context = context;
        this.countries = countries;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return countries.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        if(convertView == null) view = inflater.inflate(R.layout.list_item,null,false);
        else view = convertView;

        ((ImageView)view.findViewById(R.id.imageFlag))
                .setImageResource(countries.get(position).getCountryFlag());

        ((TextView)view.findViewById(R.id.textCountryName))
                .setText(countries.get(position).getCountryName());

        return view;
    }
}
