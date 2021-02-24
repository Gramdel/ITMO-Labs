package commands;

public class Exit extends Command {
    public Exit() {
        super(false);
    }

    @Override
    public void execute(String[] args) {
        if (rightArg(args)) {
            System.out.println("Комманда exit выполнена, программа завершает работу.");
            System.exit(0);
        }
    }

    @Override
    public String description() {
        return "Прекращает работу программы (без сохранения коллекции в файл)." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: exit";
    }
}
