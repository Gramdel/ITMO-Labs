package core;

import java.util.*;
import collection.Product;

public class Main {
    public static LinkedList<String> history = new LinkedList<>();
    public static LinkedHashSet<Product> collection = new LinkedHashSet<>();

    public static void main(String[] args) {

        boolean k = false;
        do {
            if (!k) {
                k = true;
                System.out.println("TODO \n" + "Введите команду:");
            } else if (history.peekLast().equals("exit")) break;
            else System.out.println("Чтобы прекратить ввод, введите команду exit.");

            history.add(Interpreter.read());
            if (history.size()>7) history.remove();
            //System.out.println(history);
        } while (k);
    }
}