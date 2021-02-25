package collection;

public class Coordinates {
    private final Double x; //Поле не может быть null
    private final Long y; //Поле не может быть null

    public Coordinates(Double x, Long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + "}";
    }

    public String toStringForCSV() {
        return x + "," + y;
    }
}