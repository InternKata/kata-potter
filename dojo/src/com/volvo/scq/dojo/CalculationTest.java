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
    }

    @Test
    public void testBasics() {

        assertEquals(0, calculation.basketSize());
        calculation.addBook(Arrays.asList(1));
        assertEquals(1, calculation.basketSize());

    }

    @Test
    public void testCalculating() {
       
        
        assertEquals(8, calculation.calculatePrice(), 0.0005);
    }
}
