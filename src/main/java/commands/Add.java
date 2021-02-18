package commands;

public class Add extends Command {
    public Add(){
        super(true);
    }
    @Override
    public void execute(String arg) {
        if (rightArg(arg)){
            //допишется позже
            System.out.println("Элемент добавлен в коллекцию.");
        }
    }
    @Override
    public String description() {
        return "Добавляет новый элемент в коллекцию."+syntax();
    }
    @Override
    public String syntax() {
        return " Синтаксис: add {element}";
    }
    @Override
    protected boolean rightArg(String arg) {
        if (true) {     //ещё не определился, что проверять
            System.out.println("Некоррекный формат аргумента!");
            return false;
        }
        return super.rightArg(arg);
    }
}
