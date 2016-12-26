package dto;

import java.util.ArrayList;

/**
 * Created by aniruddha on 26/12/16.
 */

public class My {
    private String name;
    private String os;
    private double ver;
    private Vesrions allVersions;
    private ArrayList<Devivce> devices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public double getVer() {
        return ver;
    }

    public void setVer(double ver) {
        this.ver = ver;
    }

    public Vesrions getAllVersions() {
        return allVersions;
    }

    public void setAllVersions(Vesrions allVersions) {
        this.allVersions = allVersions;
    }

    public ArrayList<Devivce> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Devivce> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "My{" +
                "name='" + name + '\'' +
                ", os='" + os + '\'' +
                ", ver=" + ver +
                ", allVersions=" + allVersions +
                ", devices=" + devices +
                '}';
    }
}
