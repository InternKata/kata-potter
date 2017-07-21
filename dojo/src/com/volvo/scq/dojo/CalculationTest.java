package com.volvo.scq.dojo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CalculationTest {

    private Calculation calculation;
    private Books books;

    @Before
    public void setUp() {
        calculation = new Calculation();
        books = new Books();
        calculation.setBooks(books);
    }

    @Test
    public void testBasics() {
        assertEquals(0, books.basketSize());
    }

    // @Test
    // public void testCalculating() {
    // assertEquals(8, calculation.calculatePrice(), 0.0005);
    // books.addBook(Arrays.asList(2));
    // assertEquals(15.2, calculation.calculatePrice(), 0.0005);
    // books.addBook(Arrays.asList(2));
    // assertEquals(21.6, calculation.calculatePrice(), 0.0005);
    // books.addBook(Arrays.asList(2));
    // assertEquals(25.6, calculation.calculatePrice(), 0.0005);
    // books.addBook(Arrays.asList(2));
    // assertEquals(30, calculation.calculatePrice(), 0.0005);
    // }

    @Test
    public void testCalculatePairs() {
        books.addBook(Arrays.asList(1, 1, 2, 2, 2));
        books.addBooksToMap();

        assertEquals((2 * (2 * 8)) * 0.95 + 8, calculation.getBestPrice(), 0.0005);
    }

    @Test
    public void testCalculateTriplets() {
        books.addBook(Arrays.asList(1, 1, 2, 2, 2, 1, 2, 3));
        books.addBooksToMap();

        assertEquals((3 * 8) * 0.90 + 4 * 8 * 0.95 + 8, calculation.getBestPrice(), 0.0005);
    }

    @Test
    public void testCalculateQuartets() {
        // books.addBook(Arrays.asList(1, 1, 2, 2, 2, 1, 2, 3, 4)); //1234 12 12 2
        // books.addBooksToMap();

        // assertEquals((4*8)*0.80 + 2*2*8*0.95 + 8, calculation.getBestPrice(), 0.0005);

        Books secondBooks = new Books();

        secondBooks.addBook(Arrays.asList(2, 2, 2, 2, 1, 1, 3, 3, 4, 4)); // 1234 1234 22
        secondBooks.addBooksToMap();
        calculation.setBooks(secondBooks);

        assertEquals(2 * (4 * 8) * 0.80 + 2 * 8, calculation.getBestPrice(), 0.0005);

        Books thirdBooks = new Books();

        thirdBooks.addBook(Arrays.asList(1, 2, 3, 4, 1, 2, 2, 1, 3));
        thirdBooks.addBooksToMap();
        calculation.setBooks(thirdBooks);
        assertEquals((4 * 8) * 0.80 + (3 * 8) * 0.90 + (2 * 8) * 0.95, calculation.getBestPrice(), 0.0005);
    }

    @Test
    public void testCalculateQuintets() {
        books.addBook(Arrays.asList(1, 1, 2, 2, 3, 1, 2, 3, 4, 5)); // 1234 1235 123 12
        books.addBooksToMap();

        assertEquals(2*(4 * 8) * 0.8 + 2 * 8 * 0.95, calculation.getBestPrice(), 0.0005);
        Books thirdBooks = new Books();

        thirdBooks.addBook(Arrays.asList(5, 5, 5, 5, 5,
                                         1, 1, 1, 1, 1, 
                                         2, 2, 2, 2, 
                                         3, 3, 3, 3, 3, 
                                         4, 4, 4, 4));
        thirdBooks.addBooksToMap();
        calculation.setBooks(thirdBooks);

        assertEquals(3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8),calculation.calculateQuintets(thirdBooks.getBooksMap(), 
                                                                                          thirdBooks.basketSize()), 0.0005);
    }

    @Test
    public void testPrices() {
        assignBooks(0, Arrays.asList());
        assignBooks(8, Arrays.asList(5));
        assignBooks(8, Arrays.asList(1));
        assignBooks(8, Arrays.asList(2));
        assignBooks(8, Arrays.asList(3));
        assignBooks(8, Arrays.asList(4));
        assignBooks(8 * 2, Arrays.asList(5, 5));
        assignBooks(8 * 3, Arrays.asList(1, 1, 1));
        
        assignBooks(8 * 2 * 0.95, Arrays.asList(5, 1));
        assignBooks(8 * 3 * 0.9,  Arrays.asList(5, 2, 4));
        assignBooks(8 * 4 * 0.8,  Arrays.asList(5, 1, 2, 4));
        assignBooks(8 * 5 * 0.75, Arrays.asList(5, 1, 2, 3, 4));
        
        assignBooks(8 + (8 * 2 * 0.95), Arrays.asList(5, 5, 1));
        assignBooks(2 * (8 * 2 * 0.95), Arrays.asList(5, 5, 1, 1));
        assignBooks((8 * 4 * 0.8) + (8 * 2 * 0.95), Arrays.asList(5, 5, 1, 2, 2, 3));
        assignBooks(8 + (8 * 5 * 0.75), Arrays.asList(5, 1, 1, 2, 3, 4));
        
       assignBooks(2 * (8 * 4 * 0.8), Arrays.asList(5, 5, 1, 1, 2, 2, 3, 4));
       assignBooks(3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8), 
         Arrays.asList(5, 5, 5, 5, 5,
                 1, 1, 1, 1, 1, 
                 2, 2, 2, 2, 
                 3, 3, 3, 3, 3, 
                 4, 4, 4, 4));
    }

    private void assignBooks(double expectedValue, List<Integer> books) {
        Books thirdBooks = new Books();

        thirdBooks.addBook(books);
        thirdBooks.addBooksToMap();
        calculation.setBooks(thirdBooks);
        assertEquals(expectedValue, calculation.getBestPrice(), 0.0005);
    }
}
