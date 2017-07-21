package com.volvo.scq.dojo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class QuintetsTest {
    private Books books;
    private Quintets quintets;

    @Before
    public void setUp() {
        books = new Books();
        quintets = new Quintets(books);
        books.addBook(Arrays.asList(1));
    }


    @Test
    public void testCountQuartets() {
        books.addBook(Arrays.asList(2, 2, 2, 1));
        books.addBooksToMap();

        assertEquals(0, quintets.getGroups(books.getBooksMap()));
        
        Books secondBooks = new Books();
        Quintets secondQuintet = new Quintets(secondBooks);

        secondBooks.addBook(Arrays.asList(2, 2, 2, 2, 1, 1, 3, 4, 4, 5));
        secondBooks.addBooksToMap();

        assertEquals(1, secondQuintet.getGroups(secondBooks.getBooksMap()));  
        
    }
}
