package com.volvo.scq.dojo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class CalculationTest {

    private Calculation calculation;

    @Before
    public void setUp() {
        calculation = new Calculation();
        calculation.addBook(Arrays.asList(1));
    }

    @Test
    public void testBasics() {  
        assertEquals(1, calculation.basketSize());
    }

    @Test
    public void testCalculating() {
        assertEquals(8, calculation.calculatePrice(), 0.0005);
        calculation.addBook(Arrays.asList(2));
        assertEquals(15.2, calculation.calculatePrice(), 0.0005);
        calculation.addBook(Arrays.asList(2));
        assertEquals(21.6, calculation.calculatePrice(), 0.0005);
        calculation.addBook(Arrays.asList(2));
        assertEquals(25.6, calculation.calculatePrice(), 0.0005);
        calculation.addBook(Arrays.asList(2));
        assertEquals(30, calculation.calculatePrice(), 0.0005);
    }
    
    @Test
    public void testMap() {
        calculation.addBook(Arrays.asList(2, 2, 1));
        calculation.differentBooks();
        assertEquals(2, calculation.getBooksMap().get(1).intValue());
        assertEquals(2, calculation.getBooksMap().get(2).intValue());
    }
    
    @Test
    public void testDiscounts(){
        
        calculation.addBook(Arrays.asList(2));
        calculation.differentBooks();
        assertEquals(2, calculation.findDifferentBooks());
        
    }
    
}
