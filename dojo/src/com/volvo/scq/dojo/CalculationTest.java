package com.volvo.scq.dojo;

import static com.volvo.scq.dojo.Calculation.PRICE;
import static com.volvo.scq.dojo.Calculation.discountValues;
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
    public void shouldReturnZeroWhenBasketIsEmpty() {
        assertEquals(0, books.basketSize());
    }

    @Test
    public void shouldCalculatePriceForPairsWhenExists() {
        books.addBook(Arrays.asList(1, 1, 2, 2, 2));
        books.addBooksToMap();

        assertEquals((2 * (2 * PRICE)) * discountValues[1] + PRICE, calculation.getBestPrice(), 0.0005);
    }

    @Test
    public void shouldCalculatePriceForTripletsWhenExists() {
        books.addBook(Arrays.asList(1, 1, 2, 2, 2, 1, 2, 3));
        books.addBooksToMap();

        assertEquals((3 * PRICE) * discountValues[2] + 4 * PRICE * discountValues[1] + PRICE, calculation.getBestPrice(), 0.0005);
    }

    @Test
    public void shouldCalculatePriceForQuartetsWhenExists() {
        books.addBook(Arrays.asList(1, 1, 2, 2, 2, 1, 2, 3, 4)); // 1234 12 12 2
        books.addBooksToMap();

        assertEquals((4 * PRICE) * 0.80 + 2 * 2 * PRICE * 0.95 + PRICE, calculation.getBestPrice(), 0.0005);

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
    public void shouldCalculatePriceForQuintetsWhenExists() {
        books.addBook(Arrays.asList(1, 1, 2, 2, 3, 1, 2, 3, 4, 5)); // 1234 1235 123 12
        books.addBooksToMap();

        assertEquals(2 * (4 * 8) * 0.8 + 2 * 8 * 0.95, calculation.getBestPrice(), 0.0005);

    }

    @Test
    public void shouldCalculatePriceWithoutDiscountFor_5_5() {
        assignBooks(8 * 2, Arrays.asList(5, 5));
    }

    private void assignBooks(double expectedValue, List<Integer> books) {
        Books thirdBooks = new Books();

        thirdBooks.addBook(books);
        thirdBooks.addBooksToMap();
        calculation.setBooks(thirdBooks);
        assertEquals(expectedValue, calculation.getBestPrice(), 0.0005);
    }
}
