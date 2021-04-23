package hu.nive.ujratervezes.kepesitovizsga.zoo;

public abstract class ZooAnimal {

    private String name;

    private int length;

    private long weight;

    private AnimalType type;

    public ZooAnimal(String name, int length, long weight, AnimalType type) {
        this.name = name;
        this.length = length;
        this.weight = weight;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public long getWeight() {
        return weight;
    }

    public AnimalType getType() {
        return type;
    }
}
