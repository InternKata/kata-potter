package com.volvo.scq.dojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculation {

    final static int PRICE = 8;

    final static float discountValues[] = { 1, 0.95f, 0.90f, 0.80f, 0.75f };

    private List<Integer> basket = new ArrayList<Integer>();

    private Map<Integer, Integer> booksMap = new HashMap<Integer, Integer>();

    public Calculation() {
        for (int i = 1; i < 6; i++) {
            booksMap.put(i, 0);
        }
    }

    public int basketSize() {
        return basket.size();
    }

    public void addBook(List<Integer> book) {
        basket.addAll(book);
    }

    public float calculatePrice() {
        switch (this.basketSize()) {
        case 0:
            return 0;
        case 1:
            return PRICE;
        default:
            return calculateDiscount();
        }
    }

    public float calculateDiscount() {

        switch (basketSize()) {
        case 2:
            return (PRICE * 2) * discountValues[1];
        case 3:
            return (PRICE * 3) * discountValues[2];
        case 4:
            return (PRICE * 4) * discountValues[3];
        case 5:
            return (PRICE * 5) * discountValues[4];
        default:
            return 0;
        }
    }

    public void differentBooks() {
        Collections.sort(basket);
        for (int book : basket) {
            booksMap.replace(book, booksMap.get(book) + 1);
        }
    }

    public Map<Integer, Integer> getBooksMap() {
        return booksMap;
    }

    public int findDifferentBooks() {
        int differentBooks = 0;
        for (int i = 1; i < 6; i++) {
            if (booksMap.get(i) > 0)
                differentBooks++;

        }

        return differentBooks;
    }

    public float getBestPrice() {

        float price = 0;
        int differentBooks = findDifferentBooks();

        price = basket.size() * 8;

        return price;
    }

    public int getPairs(Map<Integer, Integer> booksMap) {
        int pairs = 0;
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < 6; i++) {
            if (booksMap.get(i) > max) {
                max = booksMap.get(i);
            }
            if (booksMap.get(i) < min) {
                min = booksMap.get(i);
            }
        }
        
        
        return pairs;
    }

}
