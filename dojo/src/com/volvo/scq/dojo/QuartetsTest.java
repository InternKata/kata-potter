package com.volvo.scq.dojo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class QuartetsTest {
    private Books books;
    private Quartets quratets;

    @Before
    public void setUp() {
        books = new Books();
        quratets = new Quartets(books);
        books.addBook(Arrays.asList(1));
    }


    @Test
    public void testCountQuartets() {
        books.addBook(Arrays.asList(2, 2, 2, 1));
        books.addBooksToMap();

        assertEquals(0, quratets.getGroups(books.getBooksMap()));
        
        Books secondBooks = new Books();
        Quartets secondQuartet = new Quartets(secondBooks);

        secondBooks.addBook(Arrays.asList(2, 2, 2, 2, 1, 1, 3, 4, 4, 5));
        secondBooks.addBooksToMap();

        assertEquals(2, secondQuartet.getGroups(secondBooks.getBooksMap()));  
        
    }
}
