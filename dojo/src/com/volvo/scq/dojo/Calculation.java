package com.volvo.scq.dojo;

import java.util.HashMap;
import java.util.Map;

public class Calculation {

    final static int PRICE = 8;
    private Books books;
    private Group pairs, triplets, quartets, quintets;

    final static float discountValues[] = { 1, 0.95f, 0.90f, 0.80f, 0.75f };

    public Calculation() {
        books = new Books();
        pairs = new Pairs(books);
        triplets = new Triplets(books);
        quartets = new Quartets(books);
        quintets = new Quintets(books);

    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    // public float calculatePrice() {
    //
    // switch (books.basketSize()) {
    // case 0:
    // return 0;
    // case 1:
    // return PRICE;
    // default:
    // return getPrice();
    // }
    // }

    public float getPrice(int level) {

        float price = 0;

        Map<Integer, Integer> booksMap = new HashMap<Integer, Integer>(books.getBooksMap());
        switch (level) {
        case 1:
            price = PRICE * books.basketSize();
            break;
        case 2:
            price = calculatePairs(booksMap, books.basketSize());
            break;
        case 3:
            price = calculateTriplets(booksMap, books.basketSize());
            break;
        case 4:
            price = calculateQuartets(booksMap, books.basketSize());
            break;
        case 5:
            price = calculateQuintets(booksMap, books.basketSize());
            break;
        default:
            price = 0;
        }

        return price;
    }

    public float calculatePairs(Map<Integer, Integer> booksMap, int basketSize) {
        float price;

        int pairs = this.pairs.getGroups(booksMap);
        basketSize -= pairs * 2;
        price = (pairs * (2 * PRICE)) * discountValues[1] + basketSize * PRICE;

        return price;
    }

    public float calculateTriplets(Map<Integer, Integer> booksMap, int basketSize) {
        float price;

        int triplets = this.triplets.getGroups(booksMap);
        basketSize -= triplets * 3;
        float price2 = calculatePairs(booksMap, basketSize);
        price = (triplets * (3 * PRICE)) * discountValues[2];

        return price + price2;
    }

    public float calculateQuartets(Map<Integer, Integer> booksMap, int basketSize) {
        float price;

        int quartets = this.quartets.getGroups(booksMap);
        basketSize -= quartets * 4;
        Map<Integer, Integer> booksMapPairs = new HashMap<>(booksMap);
        Map<Integer, Integer> booksMapTriplets = new HashMap<>(booksMap);
        float pricePairs = calculatePairs(booksMapPairs, basketSize);
        float priceTriplets = calculateTriplets(booksMapTriplets, basketSize);
        price = (quartets * (4 * PRICE)) * discountValues[3];

        return price + ((pricePairs > priceTriplets) ? priceTriplets : pricePairs);
    }

    public float calculateQuintets(Map<Integer, Integer> booksMap, int basketSize) {
        float price;

        int quintets = this.quintets.getGroups(booksMap);
        basketSize -= quintets * 5;
        Map<Integer, Integer> booksMapPairs = new HashMap<>(booksMap);
        Map<Integer, Integer> booksMapTriplets = new HashMap<>(booksMap);
        Map<Integer, Integer> booksMapQuartets = new HashMap<>(booksMap);
        float pricePairs = calculatePairs(booksMapPairs, basketSize);
        float priceTriplets = calculateTriplets(booksMapTriplets, basketSize);
        float priceQuartets = calculateQuartets(booksMapQuartets, basketSize);
        price = (quintets * (5 * PRICE)) * discountValues[4];

        float bestPrice = pricePairs;
        if (bestPrice > priceTriplets) {
            bestPrice = priceTriplets;
        }
        if (bestPrice > priceQuartets) {
            bestPrice = priceQuartets;
        }
        return price + bestPrice;
    }

    public float getBestPrice() {
        float price = Integer.MAX_VALUE;
        int level = books.findDifferentBooks(books.getBooksMap());
        float result = 0f;
        if (level == 0) {
            return 0;
        }
        while (level > 0) {
            result = getPrice(level);
            level--;
            if (result < price) {
                price = result;
            }
        }

        return price;
    }

}
