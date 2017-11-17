package edu.csupomona.cs480.data;


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
