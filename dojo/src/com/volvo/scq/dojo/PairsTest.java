package com.volvo.scq.dojo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class PairsTest {

    private Books books;
    private Pairs pairs;

    @Before
    public void setUp() {
        books = new Books();
        pairs = new Pairs(books);
        books.addBook(Arrays.asList(1));
    }

    @Test
    public void testPairs() {
        books.addBook(Arrays.asList(2, 2, 1, 3, 5));
        books.addBooksToMap();

        assertEquals(3, pairs.getGroups(books.getBooksMap()));
        assertEquals(2, books.getBooksMap().get(2).intValue());
    }


}
