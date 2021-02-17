package commands;

public class ExecuteScript extends Command {
    public ExecuteScript(){
        super(true);
    }
    @Override
    public void execute(String arg) {
        System.out.println("Комманда выполнена. Аргумент: "+arg);
    }
    @Override
    public String description() {
        return "Добавляет новый элемент в коллекцию. Синтаксис: add {element}";
    }
}
