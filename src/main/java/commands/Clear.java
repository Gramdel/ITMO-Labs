package commands;

import static core.Main.collection;

public class Clear extends Command {
    public Clear() {
        super(false);
    }

    @Override
    public void execute(String[] args) {
        if (rightArg(args)) {
            collection.clear();
            System.out.println("Коллекция очищена.");
        }
    }

    @Override
    public String description() {
        return "Очищает коллекцию." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: clear";
    }
}
