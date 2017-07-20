package com.volvo.scq.dojo;

import java.util.Map;

public class Triplets implements Group {
    
    private Books books;
    
    public Triplets(Books books){
        this.books = books;
    }

    public MinMax findMinMax(Map<Integer, Integer> booksMap) {
        MinMax val = new MinMax();

        for (int i = 1; i < 6; i++) {
            if (booksMap.get(i) > val.getMax()) {
                val.setMax(booksMap.get(i));
                val.setKeyMax(i);
            }
        }

        for (int i = 1; i < 5; i++) {
            if (booksMap.get(i) > 0 && i != val.getKeyMax()) {
               val.setMin(booksMap.get(i),i);
               val.setKeyMin(i, i);
            }
        }
        return val;
    }
    
    public int getTriplets(Map<Integer, Integer> booksMap){
        return 0;
    }

}