package hu.nive.ujratervezes.kepesitovizsga.finelongword;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FineLongWordTest {

    private FineLongWord flw = new FineLongWord();

    @Test
    void testReadFineLongWordFromFileAndGetItInAnArrayWithFirstWord() {
        char[] expected = flw.readFineLongWordFromFileAndGetItInAnArray("longword1.txt");

        assertEquals(18, expected.length);
        assertEquals('A', expected[2]);
        assertEquals('W', expected[12]);
    }

    @Test
    void testReadFineLongWordFromFileAndGetItInAnArrayWithSecondWord() {
        char[] expected = flw.readFineLongWordFromFileAndGetItInAnArray("longword2.txt");

        assertEquals(29, expected.length);
        assertEquals('P', expected[4]);
        assertEquals('X', expected[21]);
    }

    @Test
    void testReadFineLongWordFromFileAndGetItInAnArrayWithThirdWord() {
        char[] expected = flw.readFineLongWordFromFileAndGetItInAnArray("longword3.txt");

        assertEquals(17, expected.length);
        assertEquals('I', expected[3]);
        assertEquals('C', expected[15]);
    }
}