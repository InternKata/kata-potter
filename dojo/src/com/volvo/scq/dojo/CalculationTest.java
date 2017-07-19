package com.volvo.scq.dojo;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculationTest {
    
    @Test
    public void testBasics() {
        
        Calculation calculation = new Calculation();
        
        assertEquals(1, calculation.basketSize());
        
    }
}
