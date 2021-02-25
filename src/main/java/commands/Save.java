package commands;

import static core.IOUnit.toCSV;
import static core.Main.collectionFile;

public class Save extends Command {
    public Save() {
        super(false);
    }

    @Override
    public void execute(String[] args) {
        if (rightArg(args)) {
            toCSV(collectionFile);
        }
    }

    @Override
    public String description() {
        return "Сохраняет коллекцию." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: save";
    }
}

