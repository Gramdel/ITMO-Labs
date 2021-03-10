package core;

import collection.Product;
import collection.Organization;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    private static final LinkedHashSet<Product> collection = new LinkedHashSet<>();
    private static final ArrayList<Organization> organizations = new ArrayList<>();
    private static final Interpreter interpreter = new Interpreter();
    private static Date date;
    private static String collectionFile;

    public static void main(String[] args) {
        collectionFile = args[0];
        date = new Date();
        IOUnit.fromCSV(args[0]);
        System.out.println("Вас приветствует программа для управления коллекцией продуктов. Для получения списка команд введите help. \n" + "Введите команду:");
        interpreter.fromStream(System.in);
    }

    public static Date getDate() {
        return date;
    }

    public static LinkedHashSet<Product> getCollection() {
        return collection;
    }

    public static ArrayList<Organization> getOrganizations() {
        return organizations;
    }

    public static Interpreter getInterpreter() {
        return interpreter;
    }

    public static String getCollectionFile() {
        return collectionFile;
    }
}