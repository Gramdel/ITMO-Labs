package commands;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;
import static core.Main.getInterpreter;

public class ExecuteScript extends Command {
    private final Stack<String> scripts = new Stack<>();

    public ExecuteScript() {
        super(1);
    }

    @Override
    public void execute(ArrayList<String> args, Command caller) throws ExecuteException {
        rightArg(args);
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(args.get(0)));
            System.out.println("Скрипт из файла " + args.get(0) + " начинает выполняться...");

            scripts.push(args.get(0));
            getInterpreter().setCaller(this);
            getInterpreter().fromStream(stream);
            if (caller == null) scripts.clear();

            System.out.println("Скрипт из файла " + args.get(0) + " выполнен!");
        } catch (FileNotFoundException e) {
            throw new ExecuteException("Скрипт с именем " + args.get(0) + " не существует!");
        }
    }

    @Override
    public String description() {
        return "Выполняет скрипт." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: execute_script file_name, где file_name - полное (с расширением) имя файла.";
    }

    @Override
    protected void rightArg(ArrayList<String> args) throws ExecuteException{
        super.rightArg(args);
        if (scripts.contains(args.get(0))) {
            throw new ExecuteException("Скрипт " + args.get(0) + " вызывает сам себя!");
        }
    }
}
