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
        calculation.addBooksToMap();
        assertEquals(2, calculation.getBooksMap().get(1).intValue());
        assertEquals(2, calculation.getBooksMap().get(2).intValue());
    }
    
    @Test
    public void testDiscounts(){
        
        calculation.addBook(Arrays.asList(2));
        calculation.addBooksToMap();
        assertEquals(2, calculation.findDifferentBooks());
        
    }
    
    @Test
    public void testMinMax(){
        calculation.addBook(Arrays.asList(2, 2, 1, 3, 5));
        calculation.addBooksToMap();
        Pairs p = new Pairs();
        MinMax minMax = p.findMinMax(calculation.getBooksMap());
        assertEquals(1, minMax.getMin(0));
        assertEquals(3, minMax.getKeyMin());
        
        assertEquals(2 ,minMax.getMax());
        assertEquals(1, minMax.getKeyMax());
        
        Calculation calc2 = new Calculation();
        calc2.addBook(Arrays.asList(1, 1, 2, 2));
        calc2.addBooksToMap();
        MinMax minMax2 = p.findMinMax(calc2.getBooksMap());
        assertEquals(2, minMax2.getMin(0));
        assertEquals(2, minMax2.getKeyMin());
        
        assertEquals(2,minMax2.getMax());
        assertEquals(1, minMax2.getKeyMax());
        
    }
    
    @Test
    public void testPairs() {
        calculation.addBook(Arrays.asList(2, 2, 1, 3, 5));
        calculation.addBooksToMap();
        
        assertEquals(3, calculation.getPairs(calculation.getBooksMap()));
        assertEquals(2, calculation.getBooksMap().get(2).intValue());
    }
    
}
