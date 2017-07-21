package com.volvo.scq.dojo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TripletsTest {

    private Books books;
    private Triplets triplet;

    @Before
    public void setUp() {
        books = new Books();
        triplet = new Triplets(books);
        books.addBook(Arrays.asList(1));
    }

    // @Test
    // public void testPairs() {
    // books.addBook(Arrays.asList(2, 2, 1, 3, 5));
    // books.addBooksToMap();
    //
    // //assertEquals(3, triplet.getPairs(books.getBooksMap()));
    // assertEquals(2, books.getBooksMap().get(2).intValue());
    // }

    @Test
    public void testMinMax() {
        books.addBook(Arrays.asList(2, 2, 2, 1, 3, 5));
        books.addBooksToMap();

        MinMax minMax = triplet.findMinMax(books.getBooksMap());
        minMax.sort();
        assertEquals(1, minMax.getMin(0));
        assertEquals(1, minMax.getMin(1));
        assertEquals(2, minMax.getMin(2));
        assertEquals(3, minMax.getKeyMin(0));
        assertEquals(5, minMax.getKeyMin(1));
        assertEquals(1, minMax.getKeyMin(2));

        assertEquals(3, minMax.getMax());
        assertEquals(2, minMax.getKeyMax());

        Books book2 = new Books();
        book2.addBook(Arrays.asList(1, 1, 2, 2));
        book2.addBooksToMap();
        MinMax minMax2 = triplet.findMinMax(book2.getBooksMap());
        minMax2.sort();
        assertEquals(2, minMax2.getMin(0));
        assertEquals(2, minMax2.getKeyMin(0));

        assertEquals(2, minMax2.getMax());
        assertEquals(1, minMax2.getKeyMax());

    }

    @Test
    public void testCountTriplets() {
        books.addBook(Arrays.asList(2, 2, 2, 1));
        books.addBooksToMap();

        MinMax minMax = triplet.findMinMax(books.getBooksMap());

        assertEquals(0, triplet.getTriplets(books.getBooksMap()));

        
        
        Books secondBooks = new Books();
        Triplets secondTriplet = new Triplets(secondBooks);

        secondBooks.addBook(Arrays.asList(2, 2, 2, 1, 1, 3, 5));
        secondBooks.addBooksToMap();

        MinMax minMax2 = secondTriplet.findMinMax(secondBooks.getBooksMap());

        assertEquals(2, secondTriplet.getTriplets(secondBooks.getBooksMap()));
    }
}
