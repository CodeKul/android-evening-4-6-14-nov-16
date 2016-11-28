package com.codekul.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 * Created by aniruddha on 28/11/16.
 */

public class CalcAdapter extends BaseAdapter{

    private Context context;
    private String []nums;
    private LayoutInflater inflater;
    private OnDigitClickListener digitClickListener;

    public interface OnDigitClickListener {
        void onDigitClick(String digit);
    }

    public CalcAdapter(Context context, String []nums, OnDigitClickListener digitClickListener) {
        this.context = context;
        this.nums = nums;
        this.digitClickListener = digitClickListener;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return nums.length;
    }

    @Override
    public Object getItem(int position) {
        return nums[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if(convertView == null) view = inflater.inflate(R.layout.calc_item,null,false);
        else view = convertView;

        ((Button)view.findViewById(R.id.btnCalc)).setText(nums[position]);
        view.findViewById(R.id.btnCalc).setOnClickListener(this::onButtonClicked);

        return view;
    }

    private void onButtonClicked(View view){
        Button btn = (Button) view;
        if(digitClickListener != null) digitClickListener.onDigitClick(btn.getText().toString());
    }
}
