package hu.nive.ujratervezes.kepesitovizsga.zoo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Zoo {

    private DataSource dataSource;

    private Set<ZooAnimal> animals = new HashSet<>();

    public Zoo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Set<ZooAnimal> getAnimals() {
        return new HashSet<>(animals);
    }

    public ZooAnimal getHeaviestAnimalInTheZoo() {
        return animals.stream().max(Comparator.comparingInt(a -> (int) a.getWeight())).get();
    }

    public int countWeights() {
        return animals.stream().mapToInt(e -> (int) e.getWeight()).sum();
    }

    public void addAnimal(ZooAnimal animal) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO animals(animal_name, length_of_member, weight, animal_type) VALUES (?, ?, ?, ?);")
        ) {
            ps.setString(1, animal.getName());
            ps.setInt(2, animal.getLength());
            ps.setLong(3, animal.getWeight());
            ps.setString(4, animal.getType().name());
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new IllegalStateException("Connection failed", sqlException);
        }
    }

    public void loadAnimals() {
        try (
                Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT animal_name, length_of_member, weight, animal_type FROM animals;")
        ) {
            while (rs.next()) {

                String name = rs.getString("animal_name");
                int length = rs.getInt("length_of_member");
                long weight = rs.getLong("weight");
                String type = rs.getString("animal_type");

                if (type.equalsIgnoreCase("lion")) {
                    animals.add(new Lion(name));
                }

                if (type.equalsIgnoreCase("giraffe")) {
                    animals.add(new Giraffe(name, length));
                }

                if (type.equalsIgnoreCase("elephant")) {
                    animals.add(new Elephant(name, length, weight));
                }
            }
        } catch (SQLException sqlException) {
            throw new IllegalStateException("Connection failed", sqlException);
        }
    }

    public ZooAnimal findExactAnimal(ZooAnimal animal) {
        return findExactAnimalByName(animal.getName());

    }

    public ZooAnimal findExactAnimalByName(String name) {
        for (ZooAnimal animal : animals) {
            if (animal.getName().equals(name)) {
                return animal;
            }
        }
        throw new IllegalArgumentException("There is no such animal in the zoo!");
    }

    public int getNumberOfAnimals() {
        return animals.size();
    }

    public List<ZooAnimal> getAnimalsOrderedByName() {
        return animals.stream().sorted().collect(Collectors.toList());
    }

    public Map<AnimalType, Long> getAnimalStatistics() {
        return animals.stream().collect(Collectors.groupingBy(ZooAnimal::getType, Collectors.counting()));
    }
}
