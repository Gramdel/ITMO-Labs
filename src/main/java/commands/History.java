package commands;

import static core.Main.*;

public class History extends Command {
    public History(){
        super(false);
    }
    @Override
    public void execute(String arg) {
        if (history.size()>0) {
            System.out.println("Комманда history выполнена, последние 7 комманд:");
            for (String s : history) System.out.println(s);
        } else {
            System.out.println("Список выполненных комманд пуст!");
        }
    }
}
