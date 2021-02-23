package commands;

import static core.Main.ioUnit;

public class Save extends Command {
    public Save(){
        super(false);
    }
    @Override
    public void execute(String[] args) {
        if (rightArg(args)){
            ioUnit.toCSV("input.txt");
            System.out.println("Коллекция сохранена.");
        }
    }
    @Override
    public String description() {
        return "Сохраняет коллекцию."+syntax();
    }
    @Override
    public String syntax() {
        return " Синтаксис: save";
    }
}

