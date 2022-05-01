package lib.collection;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Dragon implements Comparable<Dragon>, Serializable {
    private int id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private long age;
    private long weight;
    private boolean speaking;
    private DragonCharacter character;
    private Person killer;

    public Dragon(int id, String name, Coordinates coordinates, LocalDateTime creationDate, long age, long weight, boolean speaking,
                  DragonCharacter character, Person killer) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.weight = weight;
        this.speaking = speaking;
        this.character = character;
        this.killer = killer;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public long getAge() {
        return age;
    }
    public void setAge(Long age) {
        this.age = age;
    }

    public long getWeight() {
        return weight;
    }
    public void setWeight(long weight) {
        this.weight = weight;
    }

    public boolean isSpeaking() {
        return speaking;
    }
    public void setSpeaking(boolean speaking) {
        this.speaking = speaking;
    }

    public DragonCharacter getCharacter() {
        return character;
    }
    public void setCharacter(DragonCharacter character) {
        this.character = character;
    }

    public Person getKiller() {
        return killer;
    }
    public void setKiller(Person killer) {
        this.killer = killer;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name=" + name  + ", " + coordinates +
                ", creationDate=" + creationDate +
                ", age=" + age +
                ", weight=" + weight +
                ", speaking=" + speaking +
                ", character=" + character + ", " + killer;
    }

    @Override
    public int compareTo(Dragon o) {
        double comparingFlag = (this.coordinates.getX() * 10 + this.coordinates.getY() * 10 + this.age * 5 + this.weight * 10) - (o.coordinates.getX() * 10 + o.coordinates.getY() * 10 + o.getAge() * 5 + o.getWeight() * 10);
        if (comparingFlag > 0) return 1;
        if (comparingFlag < 0) return -1;
        return 0;
    }
}
