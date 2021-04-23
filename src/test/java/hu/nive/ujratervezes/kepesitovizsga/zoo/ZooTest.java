package hu.nive.ujratervezes.kepesitovizsga.zoo;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {

    private Zoo zoo;

    @BeforeEach
    void init() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/employees?useUnicode=true");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();

        flyway.clean();
        flyway.migrate();

        zoo = new Zoo(dataSource);
        zoo.loadAnimals();
    }

    @Test
    public void testAddAnimalThroughGetter() {
        assertEquals(13, zoo.getAnimals().size());

        zoo.getAnimals().add(new Lion("Pumba"));

        assertEquals(13, zoo.getAnimals().size());
    }

    @Test
    public void testAddAnimal() {
        ZooAnimal giraffe = new Giraffe("Balboa", 3);

        assertFalse(zoo.getAnimals().contains(giraffe));
        assertEquals(13, zoo.getAnimals().size());

        zoo.addAnimal(giraffe);
        zoo.loadAnimals();

        assertTrue(zoo.getAnimals().contains(giraffe));
        assertEquals(14, zoo.getAnimals().size());
    }

    @Test
    public void testAddAnimalTwice() {
        ZooAnimal giraffe = new Giraffe("Balboa", 3);

        assertFalse(zoo.getAnimals().contains(giraffe));
        assertEquals(13, zoo.getAnimals().size());

        zoo.addAnimal(giraffe);
        zoo.loadAnimals();

        assertTrue(zoo.getAnimals().contains(giraffe));
        assertEquals(14, zoo.getAnimals().size());

        zoo.addAnimal(giraffe);
        zoo.loadAnimals();

        assertTrue(zoo.getAnimals().contains(giraffe));
        assertEquals(14, zoo.getAnimals().size());
    }

    @Test
    public void testGetHeaviestAnimalInTheZoo() {
        ZooAnimal elephant = new Elephant("Ambassador", 2, 6500);

        assertEquals(elephant, zoo.getHeaviestAnimalInTheZoo());
    }

    @Test
    public void testCountWeights() {
        assertEquals(13800, zoo.countWeights());
    }

    @Test
    public void testFindExactAnimal() {
        ZooAnimal lion = new Lion("Aaliyah");

        assertEquals(lion, zoo.findExactAnimal(new Lion("Aaliyah")));
    }

    @Test
    public void testFindExactAnimalNotExisting() {
        ZooAnimal elephant = new Elephant("Ola", 2, 5679);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> zoo.findExactAnimal(elephant));
        assertEquals("There is no such animal in the zoo!", ex.getMessage());
    }

    @Test
    public void testFindExactAnimalByName() {
        ZooAnimal elephant = new Elephant("Serafina", 1, 3100);

        assertEquals(elephant, zoo.findExactAnimalByName("Serafina"));
    }

    @Test
    public void testFindExactAnimalByNameNotExisting() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> zoo.findExactAnimalByName("xyz"));
        assertEquals("There is no such animal in the zoo!", ex.getMessage());
    }

    @Test
    public void testGetNumberOfAnimals() {
        assertEquals(13, zoo.getNumberOfAnimals());
    }

    @Test
    public void testGetAnimalsOrderedByName() {
        List<ZooAnimal> zooAnimals = zoo.getAnimalsOrderedByName();

        assertEquals("Aaliyah", zooAnimals.get(0).getName());
        assertEquals("Diana", zooAnimals.get(3).getName());
        assertEquals("Simba", zooAnimals.get(9).getName());
    }

    @Test
    public void testGetAnimalStatistics() {
        Map<AnimalType, Long> statistics = zoo.getAnimalStatistics();

        assertEquals(6, statistics.get(AnimalType.LION));
        assertEquals(4, statistics.get(AnimalType.GIRAFFE));
        assertEquals(3, statistics.get(AnimalType.ELEPHANT));
    }

}