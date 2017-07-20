package com.volvo.scq.dojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinMax {

    private List<Integer> min = new ArrayList<Integer>();
    private int max = 0;
    private List<Integer> keyMin = new ArrayList<Integer>();
    private int keyMax = 0;

    public MinMax() {
        for (int i = 0; i < 5; i++) {
            min.add(Integer.MAX_VALUE);
        }
    }

    public List<Integer> getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setMin(int index, int value) {
        this.min.set(index, value);
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getKeyMin(int index) {
        return keyMin.get(index);
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

    public int getMin(int val) {
        if (min.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return min.get(val);
    }
}
