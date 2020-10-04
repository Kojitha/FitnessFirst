package com.example.home;

public class CalorieModel {

    String id, calorie, date, feelings, notes;

    public CalorieModel(String id, String calorie, String date, String feelings, String notes) {
        this.id = id;
        this.calorie = calorie;
        this.date = date;
        this.feelings = feelings;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFeelings() {
        return feelings;
    }

    public void setFeelings(String feelings) {
        this.feelings = feelings;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
