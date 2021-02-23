package commands;

import core.Main;
import java.text.SimpleDateFormat;

import static core.Main.collection;

public class Info extends Command {
    public Info(){
        super(false);
    }
    @Override
    public void execute(String arg) {
        if (rightArg(arg)){
            System.out.println("Тип коллекции:");
            System.out.println(collection.getClass());
            System.out.println("Дата инициализации коллекции:");
            System.out.println(new SimpleDateFormat("dd.MM.yyyy k:mm").format(Main.getDate()));
            System.out.println("Количество элементов коллекции:");
            System.out.println(collection.size());
        }
    }
    @Override
    public String description() {
        return "Очищает коллекцию."+syntax();
    }
    @Override
    public String syntax() {
        return " Синтаксис: clear";
    }
}
