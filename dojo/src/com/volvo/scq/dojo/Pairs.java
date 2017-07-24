package com.volvo.scq.dojo;

import java.util.Map;

public class Pairs implements Group {
    Books books;
    private int pairs = 0;

    public Pairs(Books books) {
        this.books = books;
    }

    public int getPairs() {
        return pairs;
    }

    public void setPairs(int pairs) {
        this.pairs = pairs;
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
            if (booksMap.get(i) < val.getMin(0) && booksMap.get(i) > 0 && i != val.getKeyMax()) {
                val.setMin(booksMap.get(i), 0);
                val.setKeyMin(i, 0);
            }
        }
        return val;
    }

    public int getGroups(Map<Integer, Integer> booksMap) {
        int pairs = 0;

        MinMax minMax = findMinMax(booksMap);
        while (minMax.getMax() > 0 && books.findDifferentBooks(booksMap) > 1) {
            booksMap.replace(minMax.getKeyMax(), minMax.getMax() - 1);
            booksMap.replace(minMax.getKeyMin(0), minMax.getMin(0) - 1);
            minMax = findMinMax(booksMap);
            pairs++;
        }
        return pairs;
    }
}