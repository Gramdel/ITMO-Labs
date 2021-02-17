package commands;

public class Add extends Command {
    public Add(){
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
