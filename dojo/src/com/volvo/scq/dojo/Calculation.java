package com.volvo.scq.dojo;

public class Calculation {

    final static int PRICE = 8;
    private Books books;
    private Group pairs;

    final static float discountValues[] = { 1, 0.95f, 0.90f, 0.80f, 0.75f };

    public Calculation() {
        books = new Books();
        pairs = new Pairs(books);

    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public float calculatePrice() {

        switch (books.basketSize()) {
        case 0:
            return 0;
        case 1:
            return PRICE;
        default:
            return calculateDiscount();
        }
    }

    public float calculateDiscount() {

        switch (books.basketSize()) {
        case 2:
            return (PRICE * 2) * discountValues[1];
        case 3:
            return (PRICE * 3) * discountValues[2];
        case 4:
            return (PRICE * 4) * discountValues[3];
        case 5:
            return (PRICE * 5) * discountValues[4];
        default:
            return 0;
        }
    }

    public float getBestPrice() {

        float price = 0;
        int differentBooks = books.findDifferentBooks();

        price = books.basketSize() * 8;

        return price;
    }

    // public int getTriplets(Map<Integer, Integer> booksMap) {
    // Map<Integer, Integer> valMap = new HashMap<Integer, Integer>(booksMap);
    // int triplets = 0;
    // MinMax minMax = this.findMinMax(valMap);
    // while (minMax.getMax() > 0 && findDifferentBooks() > 2) {
    //
    // }
    //
    // return triplets;
    // }

    // public MinMax findMinMax(Map<Integer, Integer> booksMap) {
    // MinMax val = new MinMax();
    //
    // for (int i = 1; i < 6; i++) {
    // if (booksMap.get(i) > val.getMax()) {
    // val.setMax(booksMap.get(i));
    // val.setKeyMax(i);
    // }
    // }
    //
    // for (int i = 1; i < 6; i++) {
    // if (booksMap.get(i) < val.getMin() && booksMap.get(i) > 0 && i != val.getKeyMax()) {
    // val.setMin(booksMap.get(i));
    // val.setKeyMin(i);
    // }
    // }
    // return val;
    // }

}
