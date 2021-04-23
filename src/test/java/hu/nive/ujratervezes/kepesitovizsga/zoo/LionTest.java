package hu.nive.ujratervezes.kepesitovizsga.zoo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LionTest {

    @Test
    void testCreate() {
        ZooAnimal lion = new Lion("Simba");

        assertEquals("Simba", lion.getName());
        assertEquals(0, lion.getLength());
        assertEquals(0L, lion.getWeight());
        assertEquals(AnimalType.LION, lion.getType());
    }
}