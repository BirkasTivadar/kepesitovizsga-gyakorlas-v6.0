package hu.nive.ujratervezes.kepesitovizsga.digits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitsTest {

    @Test
    void testGetNumbers() {
        assertEquals(9, new Digits().getNumbers());
    }
}