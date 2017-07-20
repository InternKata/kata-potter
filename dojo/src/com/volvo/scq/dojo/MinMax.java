package com.volvo.scq.dojo;

import java.util.ArrayList;
import java.util.List;

public class MinMax {

    private List<MinsAndKeys> minsAndKeys = new ArrayList<MinsAndKeys>();
    private int max = 0;
    private int keyMax = 0;

    public MinMax() {
        for (int i = 1; i < 5; i++) {
            minsAndKeys.add(new MinsAndKeys(Integer.MAX_VALUE, i));
        }
    }

    public int getMin(int index) {
        return minsAndKeys.get(index).min;
    }

    public int getMax() {
        return max;
    }

    public void addMinAndKey(MinsAndKeys minAndKeys) {
        this.minsAndKeys.add(minAndKeys);
    }
    
    public void setMin(int min, int index) {
        minsAndKeys.get(index).min = min;
    }
    
    public void setKeyMin(int key, int index) {
        minsAndKeys.get(index).key = key;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getKeyMin(int index) {
        return minsAndKeys.get(index).key;
    }

    public int getKeyMax() {
        return keyMax;
    }

    public void setKeyMax(int keyMax) {
        this.keyMax = keyMax;
    }

}
