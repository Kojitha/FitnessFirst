package com.example.tdumy;

public class Model {
    String water,wakeup,gotup;

    public Model(){}

    public Model(String water, String wakeup, String gotup) {
        this.water = water;
        this.wakeup = wakeup;
        this.gotup = gotup;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getWakeup() {
        return wakeup;
    }

    public void setWakeup(String wakeup) {
        this.wakeup = wakeup;
    }

    public String getGotup() {
        return gotup;
    }

    public void setGotup(String gotup) {
        this.gotup = gotup;
    }
}
