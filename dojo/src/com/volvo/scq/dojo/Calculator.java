package com.volvo.scq.dojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    final static BigDecimal PRICE = new BigDecimal("8");
    private Books books;
    private Group pairs, triplets, quartets, quintets;

    final static BigDecimal discountValues[] = { BigDecimal.ONE, new BigDecimal("0.95"), new BigDecimal("0.90"), new BigDecimal("0.80"),
            new BigDecimal("0.75") };

    public Calculator() {
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

    class PriceCalculatorFactory {

        public PriceCalculator createCalculator(int level) {

            switch (level) {
            case 1:
                return null;
            case 2:
                return null;
            case 3:
                return new TripletPriceCalculator();
            case 4:
                return null;
            case 5:
                return null;
            default:
                return null;
            }
        }
    }

    interface PriceCalculator {
        BigDecimal calculatePrice(Map<Integer, Integer> booksMap, int basketSize);
    }

    class TripletPriceCalculator extends AbstractPriceCalculator {

        @Override
        public BigDecimal calculatePrice(Map<Integer, Integer> booksMap, int basketSize) {
            BigDecimal tripletPrice = BigDecimal.ZERO;
            return tripletPrice;
        }

    }

    class PairsPriceCalculator extends AbstractPriceCalculator {

        @Override
        public BigDecimal calculatePrice(Map<Integer, Integer> booksMap, int basketSize) {
            BigDecimal tripletPrice = BigDecimal.ZERO;
            return tripletPrice;
        }

    }

    abstract class AbstractPriceCalculator implements PriceCalculator {

        PriceCalculator next;

        @Override
        public BigDecimal calculatePrice(Map<Integer, Integer> booksMap, int basketSize) {
            return next.calculatePrice(booksMap, basketSize);
        }

        // List<PriceCalculator> priceCalculators;

        // @Override
        // public BigDecimal calculatePrice(Map<Integer, Integer> booksMap, int basketSize) {
        // Iterator<PriceCalculator> iterator = priceCalculators.iterator();
        // while(iterator.hasNext()) {
        // PriceCalculator calculator = iterator.next();
        // calculator.calculatePrice(booksMap, basketSize);
        // }
        // return null;
        // }

    }

    public BigDecimal getPrice(int level) {

        PriceCalculator singlePriceCalculator = new SinglePriceCalculator();
        PriceCalculator pairsPriceCalculator = new PairsPriceCalculator();
        singlePriceCalculator.setNext(pairsPriceCalculator);

        PriceCalculatorFactory priceCalculatorFactory = new PriceCalculatorFactory();

        Map<Integer, Integer> booksMap = new HashMap<Integer, Integer>(books.getBooksMap());
        PriceCalculator priceCalculator = priceCalculatorFactory.createCalculator(level);

        return priceCalculator.calculatePrice(booksMap, books.basketSize());

    }

    public BigDecimal calculatePairs(Map<Integer, Integer> booksMap, int basketSize) {
        BigDecimal price;

        int pairs = this.pairs.getGroups(booksMap);
        basketSize -= pairs * 2;
        price = (new BigDecimal(pairs).multiply(new BigDecimal("2")).multiply(PRICE).multiply(discountValues[1])
                                      .add(new BigDecimal(basketSize).multiply(PRICE)));

        return price;
    }

    public BigDecimal calculateTriplets(Map<Integer, Integer> booksMap, int basketSize) {
        BigDecimal price;

        int triplets = this.triplets.getGroups(booksMap);
        basketSize -= triplets * 3;
        BigDecimal price2 = calculatePairs(booksMap, basketSize);
        price = (new BigDecimal(triplets).multiply(new BigDecimal("3")).multiply(PRICE).multiply(discountValues[2]));

        return price.add(price2);
    }

    public BigDecimal calculateQuartets(Map<Integer, Integer> booksMap, int basketSize) {
        BigDecimal price;

        int quartets = this.quartets.getGroups(booksMap);
        basketSize -= quartets * 4;
        Map<Integer, Integer> booksMapPairs = new HashMap<>(booksMap);
        Map<Integer, Integer> booksMapTriplets = new HashMap<>(booksMap);
        BigDecimal pricePairs = calculatePairs(booksMapPairs, basketSize);
        BigDecimal priceTriplets = calculateTriplets(booksMapTriplets, basketSize);
        price = (new BigDecimal(quartets).multiply(new BigDecimal(4)).multiply(PRICE).multiply(discountValues[3]));

        return price.add(((pricePairs.compareTo(priceTriplets) == 1) ? priceTriplets : pricePairs));
    }

    public BigDecimal calculateQuintets(Map<Integer, Integer> booksMap, int basketSize) {
        BigDecimal price = BigDecimal.ZERO;
        BigDecimal pricePairs = BigDecimal.ZERO;
        BigDecimal priceTriplets = BigDecimal.ZERO;
        BigDecimal priceQuartets = BigDecimal.ZERO;
        BigDecimal bestPrice = BigDecimal.ZERO;
        BigDecimal globalPrice = new BigDecimal(books.basketSize()).multiply(PRICE);

        int quintets = this.quintets.getGroups(booksMap);
        int index = 1;

        while (quintets > 0) {

            basketSize -= 5;
            quintets--;
            Map<Integer, Integer> booksMapPairs = new HashMap<>(booksMap);
            Map<Integer, Integer> booksMapTriplets = new HashMap<>(booksMap);
            Map<Integer, Integer> booksMapQuartets = new HashMap<>(booksMap);
            pricePairs = calculatePairs(booksMapPairs, basketSize);
            priceTriplets = calculateTriplets(booksMapTriplets, basketSize);
            priceQuartets = calculateQuartets(booksMapQuartets, basketSize);
            price = (new BigDecimal(index++).multiply(PRICE.multiply(new BigDecimal("5"))).multiply(discountValues[4]));
            bestPrice = pricePairs;
            if (bestPrice.compareTo(priceTriplets) == 1) {
                bestPrice = priceTriplets;
            }
            if (bestPrice.compareTo(priceQuartets) == 1) {
                bestPrice = priceQuartets;
            }
            if (globalPrice.compareTo(bestPrice.add(price)) == 1) {
                globalPrice = bestPrice.add(price);
            }

        }

        return globalPrice;
    }

    public BigDecimal getBestPrice() {
        BigDecimal price = new BigDecimal(Integer.MAX_VALUE);
        int level = books.findDifferentBooks(books.getBooksMap());
        BigDecimal result = BigDecimal.ZERO;
        if (level == 0) {
            return BigDecimal.ZERO;
        }
        while (level > 0) {
            result = getPrice(level);
            level--;
            if (result.compareTo(price) == -1) {
                price = result;
            }
        }

        return price;
    }

}
