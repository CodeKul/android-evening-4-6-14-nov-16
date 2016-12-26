package com.codekul.jsonparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import dto.Devivce;
import dto.My;
import dto.Vesrions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //parseUsingJsonObjectApi(readJsonFromAssets());

        parseUsingGson(readJsonFromAssets());
    }

    private String readJsonFromAssets() {
        StringBuilder builder = new StringBuilder();
        try {
            InputStream is = getAssets().open("my.json");
            while(true){
                int ch = is.read();
                if(ch == -1) break;
                else builder.append((char)ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private void parseUsingJsonObjectApi(String json) {

        StringBuilder builder = new StringBuilder();

        try {
            JSONObject rootObj = new JSONObject(json);
            String name = rootObj.getString("name");
            String os = rootObj.getString("os");
            double version = rootObj.getDouble("ver");
            boolean isUpdateAva = rootObj.getBoolean("isUpdateAva");
            builder.append("\n Name ").append(name);
            builder.append("\n Os ").append(os);
            builder.append("\n version ").append(version);
            builder.append("\n Update ").append(isUpdateAva);

            JSONObject innerObj = rootObj.getJSONObject("allVersions");
            double base = innerObj.getDouble("base");
            double cupCake = innerObj.getDouble("cupCake");
            builder.append("\n Base ").append(base);
            builder.append("\n Cupcake ").append(cupCake);

            JSONArray devices = rootObj.getJSONArray("devices");

            for (int i = 0 ; i < devices.length(); i++){
                JSONObject arrObj = devices.getJSONObject(i);
                String mobile = arrObj.getString("mobile");
                int cost = arrObj.getInt("cost");
                builder.append("\n Mobile " ).append(mobile);
                builder.append("\n Cost ").append(cost);
            }

            Log.i("@codekul",builder.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseUsingGson(String json) {
        Gson gson = new Gson();
        My my = gson.fromJson(json, My.class);
        Log.i("@codekul","Gson - "+my);

        My myCon = new My();
        myCon.setName("codekul.com");
        myCon.setOs("Android");
        myCon.setVer(5.1);

        Vesrions vesrions = new Vesrions();
        vesrions.setBase("android");
        vesrions.setCupCake("androiod");
        myCon.setAllVersions(vesrions);

        ArrayList<Devivce> devices = new ArrayList<>();
        Devivce d1 = new Devivce();
        d1.setCost(800);
        d1.setMobile("android");
        myCon.setDevices(devices);

        String newJson = gson.toJson(myCon);
        Log.i("@codekul","Converted - "+newJson);
    }
}
