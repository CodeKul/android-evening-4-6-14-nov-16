package com.codekul.retainingstate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_TEXT_INFO = "textInfo";
    private static final String KEY_TEXT_ADDRESS = "textAddress";
    private static final String KEY_TEXT_PRICE = "price";
    private static final String KEY_TEXT_CASHBACK = "cashBack";

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*if(savedInstanceState  != null) {
            ((TextView)findViewById(R.id.textInfo)).setText(savedInstanceState.getString(KEY_TEXT_INFO) + " - > " + savedInstanceState.getString(KEY_TEXT_ADDRESS));
        }*/


        findViewById(R.id.btnOkay).setOnClickListener(this::clicked);
    }

    private Product getMeProduct(){

        Product product = new Product();
        product.setCashBack(10);
        product.setName("Mouse");
        product.setPrice(800);

        return product;
    }

    private void clicked(View view) {
        if(view.getId() == R.id.btnOkay) {
           product = getMeProduct();
            ((TextView)findViewById(R.id.textInfo)).setText(product.getName());
        }
    }

    @Override
    protected void onStop() {
        mt("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mt("onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mt("onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TEXT_INFO, product.getName());
        outState.putFloat(KEY_TEXT_PRICE, product.getPrice());
        outState.putInt(KEY_TEXT_CASHBACK, product.getCashBack());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState  != null)
            ((TextView)findViewById(R.id.textInfo)).setText(savedInstanceState.getString(KEY_TEXT_INFO) + " - > " + savedInstanceState.getFloat(KEY_TEXT_PRICE));
    }

    private void mt(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }
}
