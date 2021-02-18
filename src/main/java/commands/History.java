package commands;

import static core.Main.interpreter;

public class History extends Command {
    public History(){
        super(false);
    }
    @Override
    public void execute(String arg) {
        if (rightArg(arg)){
            if (interpreter.getHistory().size()>0) {
                System.out.println("Комманда history выполнена, последние 7 комманд:");
                for (String s : interpreter.getHistory()) System.out.println(s);
            } else {
                System.out.println("Список выполненных комманд пуст!");
            }
        }
    }
    @Override
    public String description() {
        return "Выводит последние 7 комманд."+syntax();
    }
    @Override
    public String syntax() {
        return " Синтаксис: history";
    }
}
