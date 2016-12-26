package com.codekul.webservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //https://github.com/toddmotto/public-apis#art--design

    //myjson.com

    // http://api.icndb.com/jokes/random/3

    //https://reqres.in/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnApi).setOnClickListener(this::click);
    }

    private void click(View view) {
       post();
    }

    private void get() {
        //show dialog
        Ws.q(this)
                .add(new StringRequest("http://api.icndb.com/jokes/random/3",
                        this::onJoke,
                        this::onJokeError));
    }

    private void onJokeError(VolleyError volleyError) {
        Log.i("@codekul","Volley Error - "+volleyError.toString());
        //dismiss dialog
    }

    private void onJoke(String s) {

        //dismiss dialog
        Log.i("@codekul","Json - "+s);

        Gson gson = new Gson();
        Joke joke = gson.fromJson(s,Joke.class);

        updateJokeList(joke);
    }

    private void post() {

        JSONObject obj = new JSONObject();
        try {
            obj.put("name","android");
            obj.put("job","os");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Ws.q(this)
                .add(new JsonObjectRequest("https://reqres.in/api/users",obj,
                        this::onPostSuccess,
                        this::onPostError));
    }

    private void onPostSuccess(JSONObject ob) {
        Log.i("@codekul","Response - "+ob.toString());
    }

    private void onPostError(VolleyError e){
        Log.i("@codekul","Error - "+e.toString());
    }


    private void updateJokeList(Joke joke){

        ArrayList<String> jokes = new ArrayList<>();
        for (JokeInfo info : joke.getValue()) {
            jokes.add(info.getJoke());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,jokes);
        ((ListView)findViewById(R.id.listJokes)).setAdapter(adapter);
    }
}
