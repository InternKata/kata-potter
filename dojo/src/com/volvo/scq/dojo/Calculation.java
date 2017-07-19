package com.volvo.scq.dojo;

import java.util.ArrayList;
import java.util.List;

public class Calculation {
    
    final static int PRICE = 8;
    
    private String[] books = {"one","two","three","four","five"};
    
    private List<Integer> basket = new ArrayList<Integer>();

    public int basketSize() {
        return basket.size();
    }
    
}
