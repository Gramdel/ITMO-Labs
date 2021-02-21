package collection;

import static core.Main.collection;

public class Product {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private String partNumber; //Значение этого поля должно быть уникальным, Поле не может быть null
    private Float manufactureCost; //Поле не может быть null
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private Organization manufacturer; //Поле может быть null

    public Product(String name,Coordinates coordinates,float price,String partNumber,Float manufactureCost,UnitOfMeasure unitOfMeasure,Organization manufacturer){
        this.id = (long) collection.size() + 1;
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "{\n" +
               "\tid = "+id+",\n"+
               "\tname = "+name+",\n"+
               "\tcoordinates = "+coordinates+",\n"+
               "\tcreationDate = "+creationDate+",\n"+
               "\tprice = "+price+",\n"+
               "\tpartNumber = "+partNumber+",\n"+
               "\tmanufactureCost = "+manufactureCost+",\n"+
               "\tunitOfMeasure = "+unitOfMeasure+",\n"+
               "\tmanufacturer = "+manufacturer+"\n"+
                "}";
    }

    public String getPartNumber() {
        return partNumber;
    }
}