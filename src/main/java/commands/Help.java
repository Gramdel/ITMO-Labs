package commands;

import java.util.Map;
import static core.Main.commands;

public class Help extends Command {
    public Help(){
        super(false);
    }
    @Override
    public void execute(String arg) {
        System.out.println("Список допустимых команд:");
        for (Map.Entry<Command,String> entry : commands.entrySet())
            System.out.println(entry.getValue()+" - "+entry.getKey().description());
    }
    @Override
    public String description() {
        return "Выводит справку по доступным коммандам. Синтаксис: help";
    }
}
