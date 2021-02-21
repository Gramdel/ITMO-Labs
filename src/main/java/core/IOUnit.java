package core;

import commands.Add;
import commands.Show;
import static core.Main.collection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IOUnit {
    public void fromCSV(String csv){
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/"+csv))) {
            Add add = new Add();
            String s;
            while ((s = reader.readLine()) != null) {
                add.setAutoMode();
                add.execute(s);
            }
            if(collection.size()==0) {
                System.out.println("Коллекция не заполнена данными, так как загрузочный файл коллекции не содержит ни одной корректной строки.");
            }
        } catch (IOException e) {
            System.out.println("Коллекция не заполнена данными, так как загрузочный файл коллекции не найден.");
        }
    }
}
