package lib.collection;

import java.io.Serializable;

public class Location implements Serializable {
    private Double x;
    private int y;
    private String name;

    public Location(Double x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "xKiller=" + x +
                ", yKiller=" + y +
                ", nameLocation=" + name;
    }
}
