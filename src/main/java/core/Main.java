package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;

import collection.Organization;
import collection.Product;

public class Main {
    public static LinkedHashSet<Product> collection = new LinkedHashSet<>();
    public static ArrayList<Organization> organizations = new ArrayList<>();
    public static Interpreter interpreter = new Interpreter();
    public static IOUnit ioUnit = new IOUnit();
    private static Date date;

    public static void main(String[] args) {
        date = new Date();
        ioUnit.fromCSV("input.txt");
        System.out.println("TODO \n" + "Введите команду:");
        interpreter.fromStream(System.in);
    }

    public static Date getDate(){
        return date;
    }
}