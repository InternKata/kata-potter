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

        assertEquals(3, pairs.getPairs(books.getBooksMap()));
        assertEquals(2, books.getBooksMap().get(2).intValue());
    }

    @Test
    public void testMinMax() {
        books.addBook(Arrays.asList(2, 2, 1, 3, 5));
        books.addBooksToMap();

        MinMax minMax = pairs.findMinMax(books.getBooksMap());
        assertEquals(1, minMax.getMin(0));
        assertEquals(3, minMax.getKeyMin(0));

        assertEquals(2, minMax.getMax());
        assertEquals(1, minMax.getKeyMax());

        Books book2 = new Books();
        book2.addBook(Arrays.asList(1, 1, 2, 2));
        book2.addBooksToMap();
        MinMax minMax2 = pairs.findMinMax(book2.getBooksMap());
        assertEquals(2, minMax2.getMin(0));
        assertEquals(2, minMax2.getKeyMin(0));

        assertEquals(2, minMax2.getMax());
        assertEquals(1, minMax2.getKeyMax());

    }

}
