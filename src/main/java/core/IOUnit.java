package core;

import collection.Product;
import commands.Add;
import static core.Main.collection;

import java.io.*;
import java.util.Iterator;

public class IOUnit {
    public void fromCSV(String csv){
        try (BufferedReader in = new BufferedReader(new FileReader("src/main/resources/"+csv))) {
            Add add = new Add();
            String s;
            while ((s = in.readLine()) != null) {
                if (s.matches("[^,]*(,[^,]*){10}")) {
                    add.setAutoMode();
                    add.execute(s.split("[,;]", -1));
                }
            }
            if(collection.size()==0) {
                System.out.println("Коллекция не заполнена данными, так как загрузочный файл коллекции не содержит ни одной корректной строки.");
            }
        } catch (IOException e) {
            System.out.println("Коллекция не заполнена данными, так как загрузочный файл коллекции не найден.");
        }
    }
    public void toCSV(String csv){
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("src/main/resources/"+csv))) {
            for (Iterator<Product> iter = collection.iterator(); iter.hasNext(); )
            {
                out.write(iter.next().toStringForCSV().getBytes());
            }
        } catch (IOException e) {
            System.out.println("Коллекция не сохранена, так как файл для сохранения коллекции не найден.");
        }
    }
}
