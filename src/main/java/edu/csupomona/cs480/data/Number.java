package edu.csupomona.cs480.data;

/**
 * Created by Connor on 10/12/2017.
 */
public class Number {

    private double num;

    public Number(double d) {
        num = d;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}
