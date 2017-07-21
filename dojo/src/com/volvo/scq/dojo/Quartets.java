package com.volvo.scq.dojo;

import java.util.HashMap;
import java.util.Map;

public class Quartets implements Group {

private Books books;
    
    public Quartets(Books books){
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

        for (int i = 1; i < 6; i++) {
            if (booksMap.get(i) > 0 && i != val.getKeyMax()) {
               val.setMin(booksMap.get(i),i);
               val.setKeyMin(i, i);
            }
        }
        return val;
    }
    
    public int getGroups(Map<Integer, Integer> booksMap){
        Map<Integer, Integer> valMap = new HashMap<Integer, Integer>(booksMap);

        int quartets = 0;

        MinMax minMax = findMinMax(valMap);
        
        while (minMax.getMax() > 0 && books.findDifferentBooks(valMap) > 3) {
            valMap.replace(minMax.getKeyMax(), minMax.getMax() - 1);
            
            valMap.replace(minMax.getKeyMin(0), minMax.getMin(0) - 1);
            valMap.replace(minMax.getKeyMin(1), minMax.getMin(1) - 1);
            valMap.replace(minMax.getKeyMin(2), minMax.getMin(2) - 1);
            minMax = findMinMax(valMap);
            quartets++;
        }

        return quartets;
    }

}