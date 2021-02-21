package core;

import java.util.LinkedHashSet;
import collection.Product;

public class Main {
    public static LinkedHashSet<Product> collection = new LinkedHashSet<>();
    public static Interpreter interpreter = new Interpreter();
    public static IOUnit ioUnit = new IOUnit();

    public static void main(String[] args) {
        ioUnit.fromCSV("input.txt");
        System.out.println("TODO \n" + "Введите команду:");
        interpreter.fromStream(System.in);
    }
}