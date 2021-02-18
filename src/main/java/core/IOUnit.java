package core;

import collection.Coordinates;
import collection.Product;
import collection.UnitOfMeasure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IOUnit {
    public void fromCSV(String csv){
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/"+csv))) {
            String s;
            while ((s = reader.readLine()) != null) {
                String[] d = s.split(",");
                for (String iterator : d){
                    System.out.println(iterator);
                }

                Main.collection.add(new Product(
                        d[0],
                        new Coordinates(Double.parseDouble(d[1]), Long.parseLong(d[2])),
                        Float.parseFloat(d[3]),
                        Float.parseFloat(d[4]),
                        UnitOfMeasure.fromString(d[5])
                ));
            }
        } catch (IOException e) {
            System.out.println("Введено имя несуществующего файла коллекции!");
        }
        System.out.println(Main.collection.toArray()[0]);
    }
}
