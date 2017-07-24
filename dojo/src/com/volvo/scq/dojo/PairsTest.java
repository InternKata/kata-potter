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
        books.addBook(Arrays.asList(2, 2, 1));
        books.addBooksToMap();
        assertEquals(2, pairs.getGroups(books.getBooksMap()));
    }

}
