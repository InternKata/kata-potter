package com.volvo.scq.dojo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class MinMaxTest {
    private Books books;
    private Triplets quratets;

    @Before
    public void setUp() {
        books = new Books();
        quratets = new Triplets(books);
        books.addBook(Arrays.asList(1));
    }

    @Test
    public void testMinMax() {
        books.addBook(Arrays.asList(2, 2, 2, 1, 3, 5, 4));
        books.addBooksToMap();

        MinMax minMax = quratets.findMinMax(books.getBooksMap());
        minMax.sort();
        assertEquals(2, minMax.getMin(0));
        assertEquals(1, minMax.getMin(1));
        assertEquals(1, minMax.getMin(2));
        assertEquals(1, minMax.getMin(3));
        assertEquals(1, minMax.getKeyMin(0));
        assertEquals(3, minMax.getKeyMin(1));
        assertEquals(4, minMax.getKeyMin(2));
        assertEquals(5, minMax.getKeyMin(3));

        assertEquals(3, minMax.getMax());
        assertEquals(2, minMax.getKeyMax());

        Books book2 = new Books();
        book2.addBook(Arrays.asList(1, 1, 2, 2));
        book2.addBooksToMap();
        MinMax minMax2 = quratets.findMinMax(book2.getBooksMap());
        minMax2.sort();
        assertEquals(2, minMax2.getMin(0));
        assertEquals(2, minMax2.getKeyMin(0));

        assertEquals(2, minMax2.getMax());
        assertEquals(1, minMax2.getKeyMax());
    }
}
