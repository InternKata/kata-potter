package com.volvo.scq.dojo;

import java.util.ArrayList;
import java.util.List;

public class MinMax {
    private List<Integer> min = new ArrayList<Integer>();
    private int max = 0;
    private int keyMin = 0;
    private int keyMax = 0;

    public List<Integer> getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setMin(List<Integer> min) {
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
