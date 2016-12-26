package com.codekul.webservices;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by aniruddha on 26/12/16.
 */

public class Ws {

    private static RequestQueue queue;

    public static RequestQueue q(Context context){
        if(queue == null)  queue  = Volley.newRequestQueue(context);
        return queue;
    }
}
