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
    private BigDecimal pairDiscount;
    private BigDecimal pairSize;
    private BigDecimal tripletDiscount;
    private BigDecimal tripletSize;
    private BigDecimal quartetDiscount;
    private BigDecimal quartetSize;
    private BigDecimal quintetDiscount;
    private BigDecimal quintetSize;

    @Before
    public void setUp() {
        calculation = new Calculation();
        books = new Books();
        calculation.setBooks(books);

        pairDiscount = discountValues[1];
        tripletDiscount = discountValues[2];
        quartetDiscount = discountValues[3];
        quintetDiscount = discountValues[4];

        pairSize = BigDecimal.valueOf(2);
        tripletSize = BigDecimal.valueOf(3);
        quartetSize = BigDecimal.valueOf(4);
        quintetSize = BigDecimal.valueOf(5);
    }

    @Test
    public void shouldReturnZeroWhenBasketIsEmpty() {
        // given
        int basketSize = books.basketSize();

        assertEquals(0, basketSize);
    }

    @Test
    public void shouldCalculatePriceForPairsWhenExists() {
        // given
        List<Integer> basket = Arrays.asList(1, 1, 2, 2, 2);
        BigDecimal numberOfPairs = BigDecimal.valueOf(2);
        BigDecimal bestPrice = BigDecimal.ZERO;

        // when
        books.addBook(basket);
        books.addBooksToMap();

        bestPrice = calculation.getBestPrice();

        // then
        BigDecimal pairPrice = pairSize.multiply(PRICE).multiply(pairDiscount);

        assertEquals(pairPrice.multiply(numberOfPairs).add(PRICE), bestPrice);
    }

    @Test
    public void shouldCalculatePriceForTripletsWhenExists() {
        // given
        List<Integer> basket = Arrays.asList(1, 1, 2, 2, 2, 1, 2, 3);
        BigDecimal numberOfPairs = BigDecimal.valueOf(2);
        BigDecimal bestPrice = BigDecimal.ZERO;

        // when
        books.addBook(basket);
        books.addBooksToMap();

        bestPrice = calculation.getBestPrice();

        // then
        BigDecimal tripletPrice = tripletSize.multiply(PRICE).multiply(tripletDiscount);
        BigDecimal pairPrice = pairSize.multiply(PRICE).multiply(pairDiscount);
        assertEquals(tripletPrice.add(pairPrice.multiply(numberOfPairs)).add(PRICE), bestPrice);
    }

    @Test
    public void shouldCalculatePriceForQuartetsWhenExists() {
        // given
        List<Integer> basket = Arrays.asList(1, 1, 2, 2, 2, 1, 2, 3, 4);
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

    // TODO: asdfasd
    // FIXME: fasgasdasd
    @Test
    public void shouldCalculatePriceForQuintetsWhenExists() {
        // given
        List<Integer> basket = Arrays.asList(1, 1, 2, 2, 3, 1, 2, 3, 4, 5);
        BigDecimal numberOfQuartets = BigDecimal.valueOf(2);

        // when
        BigDecimal bestPrice = calculatePrice(basket);

        // then
        BigDecimal quartetPrice = quartetSize.multiply(PRICE).multiply(quartetDiscount);
        BigDecimal pairPrice = pairSize.multiply(PRICE).multiply(pairDiscount);

        assertEquals(quartetPrice.multiply(numberOfQuartets).add(pairPrice), bestPrice);
    }

    @Test
    public void shouldCalculatePriceWithoutDiscountFor_5_5() {
        assignBooks(new BigDecimal(2).multiply(PRICE), Arrays.asList(5, 5));
    }

    private void assignBooks(BigDecimal expectedValue, List<Integer> booksList) {
        Books books = new Books();

        books.addBook(booksList);
        books.addBooksToMap();
        calculation.setBooks(books);
        assertEquals(expectedValue, calculation.getBestPrice());
    }

    private BigDecimal calculatePrice(List<Integer> basket) {
        Books books = new Books();

        books.addBook(basket);
        books.addBooksToMap();
        calculation.setBooks(books);

        return calculation.getBestPrice();
    }

}
