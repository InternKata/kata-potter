package com.volvo.scq.dojo;

public class MinMax {
    private int min = Integer.MAX_VALUE;
    private int max = 0;
    private int keyMin = 0;
    private int keyMax = 0;

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getKeyMin() {
        return keyMin;
    }

    public void setKeyMin(int keyMin) {
        this.keyMin = keyMin;
    }

    public int getKeyMax() {
        return keyMax;
    }

    public void setKeyMax(int keyMax) {
        this.keyMax = keyMax;
    }
    

}
