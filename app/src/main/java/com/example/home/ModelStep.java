package com.example.home;

public class ModelStep {
    String steps,type,date;

    public ModelStep(String steps, String type, String date) {
        this.steps = steps;
        this.type = type;
        this.date = date;
    }

    public ModelStep(){}

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
