package core;

import collection.Product;
import collection.Organization;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static LinkedHashSet<Product> collection = new LinkedHashSet<>();
    public static ArrayList<Organization> organizations = new ArrayList<>();
    public static Interpreter interpreter = new Interpreter();
    private static Date date;
    public static String collectionFile;

    public static void main(String[] args) {
        collectionFile = args[0];
        date = new Date();
        IOUnit.fromCSV(args[0]);
        System.out.println("TODO \n" + "Введите команду:");
        interpreter.fromStream(System.in);
    }

    public static Date getDate() {
        return date;
    }
}