package com.example.demo.model;

public class Duelist {

    private String name;
    private double accuracy;

    public Duelist(String name, double accuracy) {
        this.name = name;
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }
}
