package com.codekul.retainingstate;

/**
 * Created by aniruddha on 29/11/16.
 */

public class Product {

    private String name;
    private float price;
    private int cashBack;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCashBack() {
        return cashBack;
    }

    public void setCashBack(int cashBack) {
        this.cashBack = cashBack;
    }
}
