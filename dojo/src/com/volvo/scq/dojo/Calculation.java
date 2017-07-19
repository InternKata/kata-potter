package com.volvo.scq.dojo;

import java.util.ArrayList;
import java.util.List;

public class Calculation {
    
    final static int PRICE = 8;
    
    private List<Integer> basket = new ArrayList<Integer>();

    public int basketSize() {
        return basket.size();
    }
    
    void addBook(List<Integer> book) {
        basket.addAll(book);
    }
    
    
}
