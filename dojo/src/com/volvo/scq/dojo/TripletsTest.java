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


    @Test
    public void testCountTriplets() {
        books.addBook(Arrays.asList(2, 2, 2, 1));
        books.addBooksToMap();

        MinMax minMax = triplet.findMinMax(books.getBooksMap());
        assertEquals(0, triplet.getGroups(books.getBooksMap()));
        
        Books secondBooks = new Books();
        Triplets secondTriplet = new Triplets(secondBooks);

        secondBooks.addBook(Arrays.asList(2, 2, 2, 1, 1, 3, 4, 4, 5));
        secondBooks.addBooksToMap();

        MinMax minMax2 = secondTriplet.findMinMax(secondBooks.getBooksMap());
        assertEquals(3, secondTriplet.getGroups(secondBooks.getBooksMap()));  
        
    }
}
