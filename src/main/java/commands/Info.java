package commands;

import core.Main;
import java.text.SimpleDateFormat;

import static core.Main.collection;

public class Info extends Command {
    public Info(){
        super(false);
    }
    @Override
    public void execute(String[] args) {
        if (rightArg(args)){
            System.out.println("Тип коллекции:");
            System.out.println(collection.getClass());
            System.out.println("Дата инициализации коллекции:");
            System.out.println(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(Main.getDate()));
            System.out.println("Количество элементов коллекции:");
            System.out.println(collection.size());
        }
    }
    @Override
    public String description() {
        return "Выводит информацию о коллекции."+syntax();
    }
    @Override
    public String syntax() {
        return " Синтаксис: info";
    }
}
