package com.codekul.cutsomadapter;

/**
 * Created by aniruddha on 28/11/16.
 */

public class MyItem {

    private Long id;
    private int countryFlag;
    private String countryName;

    public MyItem(Long id, int countryFlag, String countryName) {
        this.id = id;
        this.countryFlag = countryFlag;
        this.countryName = countryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(int countryFlag) {
        this.countryFlag = countryFlag;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
