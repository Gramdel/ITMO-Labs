package commands;

import java.util.Map;

import static core.Main.interpreter;

public class Help extends Command {
    public Help() {
        super(false);
    }

    @Override
    public void execute(String[] args) {
        if (rightArg(args)) {
            System.out.println("Список допустимых команд:");
            for (Map.Entry<Command, String> entry : interpreter.getCommands().entrySet())
                System.out.println("\t" + entry.getValue() + " - " + entry.getKey().description());
        }
    }

    @Override
    public String description() {
        return "Выводит справку по доступным коммандам." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: help";
    }
}
