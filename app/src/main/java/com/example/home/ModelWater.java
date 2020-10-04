package com.example.home;

public class ModelWater {
    String water,wakeup,gotup;

    public ModelWater(){}

    public ModelWater(String water, String wakeup, String gotup) {
        this.water = water;
        this.wakeup = wakeup;
        this.gotup = gotup;
    }

    public static String covertToLiters(String water){
        double waters = Double.parseDouble(water);
        String w1 = String.valueOf(waters/1000.0);
        return w1;
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
