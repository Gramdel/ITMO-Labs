package commands;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExecuteScript extends Command {
    public ExecuteScript(){
        super(true);
    }
    @Override
    public void execute(String arg) {
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream("src/main/resources/"+arg));

            System.out.println("Скрипт из файла "+arg+" начинает выполняться...");
            core.Interpreter.read(stream);
            System.out.println("Скрипт из файла "+arg+" выполнен!");
        } catch (FileNotFoundException e) {
            System.out.println("Скрипт с таким именем не существует!");
        }
    }
    @Override
    public String description() {
        return "Исполняет скрипт из файла. Синтаксис: execute_script file_name";
    }
}
