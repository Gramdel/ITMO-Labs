package commands;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;
import static core.Main.interpreter;

public class ExecuteScript extends Command {
    private Stack<String> scripts = new Stack<>();

    public ExecuteScript() {
        super(true);
    }

    @Override
    public void execute(String[] args) {
        if (rightArg(args)) {
            try {
                BufferedInputStream stream = new BufferedInputStream(new FileInputStream("src/main/resources/" + args[0]));
                System.out.println("Скрипт из файла " + args[0] + " начинает выполняться...");

                scripts.push(args[0]);
                interpreter.fromStream(stream);
                if (interpreter.stream.equals(System.in)) scripts.clear();

                System.out.println("Скрипт из файла " + args[0] + " выполнен!");
            } catch (FileNotFoundException e) {
                System.out.println("Скрипт с именем " + args[0] + " не существует!");
            }
        }
    }

    @Override
    public String description() {
        return "Исполняет скрипт из файла." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: execute_script file_name, где file_name - полное (с расширением) имя файла.";
    }

    @Override
    protected boolean rightArg(String[] args) {
        if (super.rightArg(args)) {
            if (scripts.contains(args[0])) {
                System.out.println("Скрипт " + args[0] + " вызывает сам себя!");
            } else {
                return true;
            }
        }
        return false;
    }
}
