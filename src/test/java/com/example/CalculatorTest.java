package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void testSquareRoot() {
        assertEquals(5.0, Calculator.squareRoot(25), 0.0001);
    }

    @Test
    void testFactorial() {
        assertEquals(120, Calculator.factorial(5));
    }

    @Test
    void testNaturalLog() {
        assertEquals(0.0, Calculator.naturalLog(1), 0.0001);
    }

    @Test
    void testPower() {
        assertEquals(8.0, Calculator.power(2, 3), 0.0001);
    }
}
