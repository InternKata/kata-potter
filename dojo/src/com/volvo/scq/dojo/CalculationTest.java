package com.volvo.scq.dojo;

import static com.volvo.scq.dojo.Calculation.PRICE;
import static com.volvo.scq.dojo.Calculation.discountValues;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
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

        assertEquals((new BigDecimal("2").multiply(new BigDecimal("2")).multiply(PRICE).multiply(discountValues[1]).add(PRICE)), calculation.getBestPrice());
    }

    @Test
    public void shouldCalculatePriceForTripletsWhenExists() {
        books.addBook(Arrays.asList(1, 1, 2, 2, 2, 1, 2, 3));
        books.addBooksToMap();

        assertEquals(new BigDecimal("3").multiply(PRICE).multiply(discountValues[2]).add(new BigDecimal("4").multiply(PRICE).multiply(discountValues[1])),
                     calculation.getBestPrice());
    }

    @Test
    public void shouldCalculatePriceForQuartetsWhenExists() {
        // given
        List<Integer> basket = Arrays.asList(1, 1, 2, 2, 2, 1, 2, 3, 4);
        BigDecimal quartetSize = BigDecimal.valueOf(4);
        BigDecimal quartetDiscount = discountValues[3];
        BigDecimal pairSize = BigDecimal.valueOf(2);
        BigDecimal pairDiscount = discountValues[1];
        BigDecimal numberOfPairs = BigDecimal.valueOf(2);
        BigDecimal bestPrice = BigDecimal.ZERO;

        // when
        books.addBook(basket); // 1234 12 12 2
        books.addBooksToMap();
        bestPrice = calculation.getBestPrice();

        // then
        BigDecimal quartetPrice = quartetSize.multiply(PRICE).multiply(quartetDiscount);
        BigDecimal pairPrice = pairSize.multiply(PRICE).multiply(pairDiscount);

        assertEquals(quartetPrice.add((numberOfPairs.multiply(pairPrice)).add(PRICE)), bestPrice);
    }

    @Test
    public void shouldCalculatePriceForQuintetsWhenExists() {
        BigDecimal pairDiscount = discountValues[1];
        BigDecimal quartetDiscount = discountValues[3];
        BigDecimal bestPrice = BigDecimal.ZERO;

        bestPrice = calculation.getBestPrice();

        books.addBook(Arrays.asList(1, 1, 2, 2, 3, 1, 2, 3, 4, 5)); // 1234 1235 123 12
        books.addBooksToMap();

        assertEquals(new BigDecimal("2").multiply(new BigDecimal("4").multiply(PRICE)).multiply(quartetDiscount).add(new BigDecimal("2")).multiply(PRICE)
                                        .multiply(pairDiscount),
                     bestPrice);
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
