package commands;

import java.util.Map;
import static core.Main.commands;

public class Help extends Command {
    public Help(){
        super(false);
    }
    @Override
    public void execute(String arg) {
        for (Map.Entry entry : commands.entrySet())
            System.out.println(entry.getValue()+" - "+((Command) entry.getKey()).description());
    }
    @Override
    public String description() {
        return "Выводит справку по доступным коммандам. Синтаксис: help";
    }
}
