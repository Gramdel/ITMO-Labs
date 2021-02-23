package commands;

import collection.Product;
import static core.Main.collection;

public class Show extends Command {
    public Show(){
        super(false);
    }
    @Override
    public void execute(String arg) {
        if (rightArg(arg)){
            if (collection.size()>0) {
                System.out.println("Элементы коллекции:");
                for (Product product : collection)
                    System.out.println(product);
            } else {
                System.out.println("Коллекция пуста!");
            }
        }
    }
    @Override
    public String description() {
        return "Выводит коллекцию."+syntax();
    }
    @Override
    public String syntax() {
        return " Синтаксис: show";
    }
}
