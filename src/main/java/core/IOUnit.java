package core;

import collection.Product;
import static core.Main.collection;
import commands.Add;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;

public class IOUnit {
    public static void fromCSV(String csv) {
        try (BufferedReader in = new BufferedReader(new FileReader("src/main/resources/" + csv))) {
            Add add = new Add();
            String s;
            while ((s = in.readLine()) != null) {
                if (s.matches("[^,]*(,[^,]*){10}")) {
                    add.setAutoMode();
                    add.execute(s.split("[,]", -1));
                }
            }
            if (collection.size() == 0) {
                System.out.println("Коллекция не заполнена данными, так как файл коллекции не содержит ни одной корректной строки.");
            }
        } catch (IOException e) {
            System.out.println("Коллекция не заполнена данными, так как файл коллекции не найден.");
        }
    }

    public static void toCSV(String csv) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("src/main/resources/" + csv))) {
            for (Product product : collection) {
                out.write(product.toStringForCSV().getBytes());
            }
        } catch (IOException e) {
            System.out.println("Коллекция не сохранена, так как файл для сохранения коллекции не найден.");
        }
    }

    public static String[] parseJson(String s) throws ParseException {
        JSONObject o = (JSONObject) new JSONParser().parse(s);
        ArrayList<String> values = new ArrayList<>();

        if (o.size() == 11) {
            if (o.containsKey("name") && o.containsKey("x") && o.containsKey("y") && o.containsKey("price")
                    && o.containsKey("partNumber") && o.containsKey("manufactureCost") && o.containsKey("unitOfMeasure")) {
                values.add(o.get("name").toString());
                values.add(o.get("x").toString());
                values.add(o.get("y").toString());
                values.add(o.get("price").toString());
                values.add(o.get("partNumber").toString());
                values.add(o.get("manufactureCost").toString());
                values.add(o.get("unitOfMeasure").toString());
            }
        }
        if (o.containsKey("name2") && o.containsKey("annualTurnover") && o.containsKey("employeesCount") && o.containsKey("type")) {
            values.add(o.get("name2").toString());
            values.add(o.get("annualTurnover").toString());
            values.add(o.get("employeesCount").toString());
            values.add(o.get("type").toString());
        }

        if (values.size() != o.size()) throw new ParseException(0);

        String[] args = new String[values.size()];
        values.toArray(args);
        return args;
    }
}
