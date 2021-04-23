package hu.nive.ujratervezes.kepesitovizsga;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitsTest {

    @Test
    public void testGetNumbers() {
        assertEquals(9, new Digits().getNumbers());
    }

}