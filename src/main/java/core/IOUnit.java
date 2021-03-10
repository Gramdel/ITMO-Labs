package core;

import collection.Product;

import commands.Add;
import commands.ExecuteException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static core.Main.*;

public class IOUnit {
    public static void fromCSV(String csv) {
        try (BufferedReader in = new BufferedReader(new FileReader(csv))) {
            String s;
            while ((s = in.readLine()) != null) {
                if (s.matches("[^,]*(,[^,]*){10}")) {
                    ArrayList<String> args = new ArrayList<>(Arrays.asList(s.split("[,]", -1)));
                    Add add = new Add();
                    add.hideSuccessMsg();
                    try {
                        add.execute(args, add);
                    } catch (ExecuteException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            if (getCollection().size() == 0) {
                System.out.println("Коллекция не заполнена данными, так как файл коллекции не содержит ни одной корректной строки.");
            }
        } catch (IOException e) {
            System.out.println("Коллекция не заполнена данными, так как файл коллекции не найден.");
        }
    }

    public static void toCSV(String csv) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(csv))) {
            ArrayList<Product> sortedCollection = new ArrayList<>(getCollection());
            sortedCollection.sort(Product.byIdComparator);
            for (Product product : sortedCollection) {
                out.write(product.toStringForCSV().getBytes());
            }
            System.out.println("Коллекция сохранена в файл "+getCollectionFile()+"!");
        } catch (IOException e) {
            System.out.println("Коллекция не сохранена, так как файл для сохранения коллекции не найден.");
        }
    }

    public static ArrayList<String> parseJson(String s) throws ParseException {
        JSONObject o = (JSONObject) new JSONParser().parse(s);

        if (o.size() != 4 && o.size() != 11) {
            throw new ParseException(0);
        }

        ArrayList<String> args = new ArrayList<>();

        if (o.size() == 11) {
            if (o.containsKey("name") && o.containsKey("x") && o.containsKey("y") && o.containsKey("price")
                    && o.containsKey("partNumber") && o.containsKey("manufactureCost") && o.containsKey("unitOfMeasure")) {
                args.add(o.get("name").toString());
                args.add(o.get("x").toString());
                args.add(o.get("y").toString());
                args.add(o.get("price").toString());
                args.add(o.get("partNumber").toString());
                args.add(o.get("manufactureCost").toString());
                args.add(o.get("unitOfMeasure").toString());
            }
        }
        if (o.containsKey("name2") && o.containsKey("annualTurnover") && o.containsKey("employeesCount") && o.containsKey("type")) {
            args.add(o.get("name2").toString());
            args.add(o.get("annualTurnover").toString());
            args.add(o.get("employeesCount").toString());
            args.add(o.get("type").toString());
        }

        if (args.size() != o.size()) throw new ParseException(0);

        return args;
    }
}
