package commands;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

import static core.Main.interpreter;

public class ExecuteScript extends Command {
    private Stack<String> scripts = new Stack<>();
    private boolean inner = false;

    public ExecuteScript(){
        super(true);
    }
    @Override
    public void execute(String arg) {
        if (rightArg(arg)){
            try {
                BufferedInputStream stream = new BufferedInputStream(new FileInputStream("src/main/resources/"+arg));
                System.out.println("Скрипт из файла "+arg+" начинает выполняться...");

                scripts.push(arg);
                interpreter.fromStream(stream);
                if (!inner) scripts.clear();

                System.out.println("Скрипт из файла "+arg+" выполнен!");
            } catch (FileNotFoundException e) {
                System.out.println("Скрипт с таким именем не существует!");
            }
        }
        inner = false;
    }
    @Override
    public String description() {
        return "Исполняет скрипт из файла."+syntax();
    }
    @Override
    public String syntax() {
        return " Синтаксис: execute_script file_name";
    }

    @Override
    protected boolean rightArg(String arg) {
        if (scripts.contains(arg)) {
            System.out.println("Скрипт "+arg+" вызывает сам себя!");
            return false;
        }
        return super.rightArg(arg);
    }
    public void setInner(){
        inner = true;
    }
}
