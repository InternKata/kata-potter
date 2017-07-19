package com.volvo.scq.dojo;

import java.util.ArrayList;
import java.util.List;

public class Calculation {

    final static int PRICE = 8;

    final static float discountValues[] = { 1, 0.95f, 0.90f, 0.80f, 0.75f };

    private List<Integer> basket = new ArrayList<Integer>();

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
        
        return 0;
    }

}
