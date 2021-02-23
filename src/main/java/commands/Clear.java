package commands;

import static core.Main.collection;

public class Clear extends Command {
    public Clear(){
        super(false);
    }
    @Override
    public void execute(String arg) {
        if (rightArg(arg)){
            collection.clear();
            System.out.println("Комманда clear выполнена, коллекция очищена.");
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
