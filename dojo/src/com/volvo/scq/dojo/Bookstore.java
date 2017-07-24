package com.volvo.scq.dojo;

import java.math.BigDecimal;
import java.util.Arrays;

public class Bookstore {

    public static void main(String[] args) {

        // List<Integer> customerBasket = Arrays.asList(1, 2, 3, 3);
        //
        // Books books = new Books();
        // Calculation calculations = new Calculation();
        //
        // books.addBook(customerBasket);
        // books.addBooksToMap();
        //
        // calculations.setBooks(books);
        // calculations.getBestPrice();
        Bookstore bookstore = new Bookstore();
        Basket basket = new Basket(Arrays.asList(new Book(1), new Book(2), new Book(3), new Book(3)));
        bookstore.calculatePrice(basket);
    }

    public BigDecimal calculatePrice(Basket basket) {
        return BigDecimal.ZERO;
    }

}
