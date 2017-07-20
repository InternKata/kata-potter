package com.volvo.scq.dojo;

import java.util.Arrays;
import java.util.Map;

public class Pairs implements Group {

    public MinMax findMinMax(Map<Integer, Integer> booksMap) {
        MinMax val = new MinMax();

        for (int i = 1; i < 6; i++) {
            if (booksMap.get(i) > val.getMax()) {
                val.setMax(booksMap.get(i));
                val.setKeyMax(i);
            }
        }

        for (int i = 1; i < 6; i++) {
            if (booksMap.get(i) < val.getMin(0) && booksMap.get(i) > 0 && i != val.getKeyMax()) {
                //val.setMin(Arrays.asList(val.getMin(i)));
                val.setKeyMin(i);
            }
        }
        return val;
    }

}
