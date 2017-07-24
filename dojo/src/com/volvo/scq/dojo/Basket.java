package com.volvo.scq.dojo;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    List<Book> books = new ArrayList<Book>();

    public int getSize() {
        return books.size();
    }

    public List<Book> getBooks() {

        return books;
    }

    public void addBooks(List<Book> booksList) {
        books.addAll(booksList);
    }
}
