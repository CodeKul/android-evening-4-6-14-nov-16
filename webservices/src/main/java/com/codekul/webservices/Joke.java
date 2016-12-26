package com.codekul.webservices;

import java.util.ArrayList;

/**
 * Created by aniruddha on 26/12/16.
 */

public class Joke {

    private String type;
    private ArrayList<JokeInfo> value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<JokeInfo> getValue() {
        return value;
    }

    public void setValue(ArrayList<JokeInfo> value) {
        this.value = value;
    }
}
