package collection;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import static core.Main.getCollection;

public class Product implements Comparable<Product> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final float price; //Значение поля должно быть больше 0
    private final String partNumber; //Значение этого поля должно быть уникальным, Поле не может быть null
    private final Float manufactureCost; //Поле не может быть null
    private final UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private final Organization manufacturer; //Поле может быть null

    public Product(String name, Coordinates coordinates, float price, String partNumber, Float manufactureCost, UnitOfMeasure unitOfMeasure, Organization manufacturer) {
        this.id = createId();
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
        return id + "," + name + "," + coordinates.toStringForCSV() + "," + price + "," + partNumber + "," + manufactureCost + "," + unitOfMeasure + "," + manufacturer.toStringForCSV();
    }

    public String getPartNumber() {
        return partNumber;
    }

    public Long getId() {
        return id;
    }

    public float getPrice() {
        return price;
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

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int compareTo(Product product) {
        return this.creationDate.compareTo(product.creationDate);
    }

    public static Comparator<Product> byIdComparator = (p1, p2) -> {
        if (p1.id.equals(p2.id)) {
            System.out.println("Что-то пошло не так: у двух продуктов один id!");
            return 0;
        }
        return (p1.id < p2.id) ? -1 : 1;
    };

    public static Comparator<Product> byPriceComparator = (p1, p2) -> Float.compare(p1.price, p2.price);

    private Long createId() {
        Long id = 1L;
        boolean isUnique;
        do {
            isUnique = true;
            for (Product product : getCollection()) {
                if (product.getId().equals(id)) {
                    isUnique = false;
                    id++;
                    break;
                }
            }
        } while (!isUnique);
        return id;
    }
}