package com.volvo.scq.dojo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class CalculationTest {

    private Calculation calculation;
    private Books books;

    @Before
    public void setUp() {
        calculation = new Calculation();
        books = new Books();
        books.addBook(Arrays.asList(1));
        calculation.setBooks(books);
    }

    @Test
    public void testBasics() {
        assertEquals(1, books.basketSize());
    }

    @Test
    public void testCalculating() {
        assertEquals(8, calculation.calculatePrice(), 0.0005);
        books.addBook(Arrays.asList(2));
        assertEquals(15.2, calculation.calculatePrice(), 0.0005);
        books.addBook(Arrays.asList(2));
        assertEquals(21.6, calculation.calculatePrice(), 0.0005);
        books.addBook(Arrays.asList(2));
        assertEquals(25.6, calculation.calculatePrice(), 0.0005);
        books.addBook(Arrays.asList(2));
        assertEquals(30, calculation.calculatePrice(), 0.0005);
    }

}
