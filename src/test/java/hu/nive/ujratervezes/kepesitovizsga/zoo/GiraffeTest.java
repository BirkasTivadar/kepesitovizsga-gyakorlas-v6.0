package hu.nive.ujratervezes.kepesitovizsga.zoo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GiraffeTest {

    @Test
   void testCreate() {
        ZooAnimal giraffe = new Giraffe("Momba", 3);

        assertEquals("Momba", giraffe.getName());
        assertEquals(3, giraffe.getLength());
        assertEquals(0L, giraffe.getWeight());
        assertEquals(AnimalType.GIRAFFE, giraffe.getType());
    }

}