package hu.nive.ujratervezes.kepesitovizsga.zoo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElephantTest {

    @Test
    void testCreate() {
        ZooAnimal elephant = new Elephant("Kumba", 2, 3456);

        assertEquals("Kumba", elephant.getName());
        assertEquals(2, elephant.getLength());
        assertEquals(3456L, elephant.getWeight());
        assertEquals(AnimalType.ELEPHANT, elephant.getType());
    }

}