package commands;

import java.util.ArrayList;

import static core.IOUnit.toCSV;
import static core.Main.getCollectionFile;

public class Save extends Command {
    public Save() {
        super(0);
    }

    @Override
    public void execute(ArrayList<String> args, Command caller) throws ExecuteException {
        rightArg(args);
        toCSV(getCollectionFile());
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

