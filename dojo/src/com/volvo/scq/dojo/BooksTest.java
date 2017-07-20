package com.volvo.scq.dojo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class BooksTest {

    private Books books;

    @Before
    public void setUp() {
        books = new Books();
        books.addBook(Arrays.asList(1));
    }

    @Test
    public void testMap() {
        books.addBook(Arrays.asList(2, 2, 1));
        books.addBooksToMap();
        assertEquals(2, books.getBooksMap().get(1).intValue());
        assertEquals(2, books.getBooksMap().get(2).intValue());
    }

    @Test
    public void testDiscounts() {

        books.addBook(Arrays.asList(2));
        books.addBooksToMap();
        assertEquals(2, books.findDifferentBooks());

    }

}
