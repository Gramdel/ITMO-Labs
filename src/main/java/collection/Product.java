package collection;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static core.Main.collection;

public class Product implements Comparable<Product> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final float price; //Значение поля должно быть больше 0
    private final String partNumber; //Значение этого поля должно быть уникальным, Поле не может быть null
    private final Float manufactureCost; //Поле не может быть null
    private final UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private final Organization manufacturer; //Поле может быть null

    public Product(String name, Coordinates coordinates, float price, String partNumber, Float manufactureCost, UnitOfMeasure unitOfMeasure, Organization manufacturer) {
        this.id = (long) collection.size() + 1;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\tid = " + id + ",\n" +
                "\tname = " + name + ",\n" +
                "\tcoordinates = " + coordinates + ",\n" +
                "\tcreationDate = " + (DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm").format(creationDate)) + ",\n" +
                "\tprice = " + price + ",\n" +
                "\tpartNumber = " + partNumber + ",\n" +
                "\tmanufactureCost = " + manufactureCost + ",\n" +
                "\tunitOfMeasure = " + unitOfMeasure + ",\n" +
                "\tmanufacturer = " + manufacturer + "\n" +
                "}";
    }

    public String toStringForCSV() {
        return name + "," + coordinates.toStringForCSV() + "," + price + "," + partNumber + "," + manufactureCost + "," + unitOfMeasure + "," + manufacturer.toStringForCSV();
    }

    public String getPartNumber() {
        return partNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    @Override
    public int compareTo(Product product) {
        return this.creationDate.compareTo(product.creationDate);
    }
}