package com.volvo.scq.dojo;

import java.util.Map;

public class Triplets implements Group {

    public MinMax findMinMax(Map<Integer, Integer> booksMap) {
        MinMax val = new MinMax();

        for (int i = 1; i < 6; i++) {
            if (booksMap.get(i) > val.getMax()) {
                val.setMax(booksMap.get(i));
                val.setKeyMax(i);
            }
        }

        for (int i = 1; i < 6; i++) {
            if (booksMap.get(i) > 0 && i != val.getKeyMax()) {
               
            }
        }
        return val;
    }

}