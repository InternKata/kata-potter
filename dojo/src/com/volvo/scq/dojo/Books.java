package com.volvo.scq.dojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Books {

    private Map<Integer, Integer> booksMap = new HashMap<Integer, Integer>();
    private List<Integer> basket = new ArrayList<Integer>();

    public static void main(String[] args) {

        List<Integer> customerBasket = Arrays.asList(1, 2, 3, 3);

        Books books = new Books();
        Calculator calculations = new Calculator();

        books.addBook(customerBasket);
        books.addBooksToMap();

        calculations.setBooks(books);
        calculations.getBestPrice();

    }

    public Books() {
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

    public void addBooksToMap() {
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

    public int findDifferentBooks(Map<Integer, Integer> booksMap) {
        int differentBooks = 0;
        for (int i = 1; i < 6; i++) {
            if (booksMap.get(i) > 0)
                differentBooks++;

        }

        return differentBooks;
    }

}
