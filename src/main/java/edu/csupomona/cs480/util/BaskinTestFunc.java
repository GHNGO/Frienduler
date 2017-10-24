package edu.csupomona.cs480.util;

import java.util.ArrayList;

/**
 * Connor Baskin A6
 */
public class BaskinTestFunc {

    private ArrayList<Double> doubleArrayList = new ArrayList<>();

    public double log2 (double num) {
        return Math.log10(num)/Math.log10(2);
    }

    public ArrayList<Double> getDoubleArrayList() {
        return doubleArrayList;
    }

    public void setDoubleArrayList(ArrayList<Double> doubleArrayList) {
        this.doubleArrayList = doubleArrayList;
    }


}
