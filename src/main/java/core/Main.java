package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import collection.Coordinates;
import java.util.HashMap;
import collection.OrganizationType;
import collection.Product;
import collection.UnitOfMeasure;
import commands.*;
import static commands.History.history;

public class Main {
    public static HashMap<Command,String> commands = new HashMap<>();
    public static LinkedHashSet<Product> collection = new LinkedHashSet<>();

    public static void main(String[] args) {

        commands.put(new Add(),"add");
        commands.put(new Clear(),"clear");
        commands.put(new Exit(),"exit");
        commands.put(new History(),"history");
        commands.put(new Help(),"help");
        commands.put(new ExecuteScript(),"execute_script");

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/"+"input.txt"))) {
            String s;
            while ((s = reader.readLine()) != null) {
                String[] d = s.split(",");
                //for (String iterator : d){
                    //System.out.println(iterator);
                //}
                collection.add(new Product(
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

        System.out.println("TODO \n" + "Введите команду:");
        Interpreter.read(System.in);
        //System.out.println(collection.toArray()[0]);
    }
}