package com.volvo.scq.dojo;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class CalculationTest {

    @Test
    public void testBasics() {

        Calculation calculation = new Calculation();
        assertEquals(0, calculation.basketSize());
        calculation.addBook(Arrays.asList(1));
        assertEquals(1, calculation.basketSize());

    }
}
