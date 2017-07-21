package com.volvo.scq.dojo;

import java.util.HashMap;
import java.util.Map;

public class Quintets implements Group{

private Books books;
    
    public Quintets(Books books){
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
        val.sort();
        return val;
    }
    
    public int getGroups(Map<Integer, Integer> booksMap){

        int quintets = 0;

        MinMax minMax = findMinMax(booksMap);
        
        while (minMax.getMax() > 0 && books.findDifferentBooks(booksMap) > 4) {
            booksMap.replace(minMax.getKeyMax(), minMax.getMax() - 1);
            
            booksMap.replace(minMax.getKeyMin(0), minMax.getMin(0) - 1);
            booksMap.replace(minMax.getKeyMin(1), minMax.getMin(1) - 1);
            booksMap.replace(minMax.getKeyMin(2), minMax.getMin(2) - 1);
            booksMap.replace(minMax.getKeyMin(3), minMax.getMin(3) - 1);
            
            minMax = findMinMax(booksMap);
            quintets++;
        }

        return quintets;
    }
}
