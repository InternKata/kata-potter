package com.volvo.scq.dojo;

import java.util.HashMap;
import java.util.Map;

public class Calculation {

    final static int PRICE = 8;
    private Books books;
    private Group pairs, triplets, quartets, quintets;

    final static float discountValues[] = { 1, 0.95f, 0.90f, 0.80f, 0.75f };

    public Calculation() {
        books = new Books();
        pairs = new Pairs(books);
        triplets = new Triplets(books);
        quartets = new Quartets(books);
        quintets = new Quintets(books);

    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public float calculatePrice() {

        switch (books.basketSize()) {
        case 0:
            return 0;
        case 1:
            return PRICE;
        default:
            return getPrice();
        }
    }

    // public float calculateDiscount() {
    //
    // switch (books.basketSize()) {
    // case 2:
    // return (PRICE * 2) * discountValues[1];
    // case 3:
    // return (PRICE * 3) * discountValues[2];
    // case 4:
    // return (PRICE * 4) * discountValues[3];
    // case 5:
    // return (PRICE * 5) * discountValues[4];
    // default:
    // return 0;
    // }
    // }
    //
    public float getPrice() {
        
        float price = 0;
        
        Map<Integer, Integer> booksMap = new HashMap<Integer, Integer>(books.getBooksMap());
        switch (books.findDifferentBooks(booksMap)) {
        case 2:
            price = calculatePairs(booksMap);

        }

        return price;
    }

    public float calculatePairs(Map<Integer, Integer> booksMap) {
        float price;
//        Map<Integer, Integer> booksMap2 = new HashMap<Integer, Integer>(books.getBooksMap());

        int basketSize = books.basketSize();
        int pairs = this.pairs.getGroups(booksMap);
        basketSize -= pairs * 2;
        price = (pairs * (2 * PRICE)) * discountValues[1] + basketSize * PRICE;

        return price;
    }

    public float getBestPrice() {

        float price = 0;
        int differentBooks = books.findDifferentBooks();

        price = books.basketSize() * 8;

        return price;
    }

}
