package commands;

import static core.Main.interpreter;

public class AddIfMax extends Command{
    public AddIfMax() {
        super(false);
    }

    @Override
    public void execute(String[] args) {
        hasArgs = !interpreter.stream.equals(System.in);
        if (rightArg(args)) {
            Add add = new Add();
            add.isAddIfMax();
            add.execute(args);
        }
    }

    @Override
    public String description() {
        return "Добавляет новый элемент в коллекцию, если его цена превышает наибольшую цену элемента в коллекции." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: add_if_max \n\t\t(В скриптах - add_if_max {element}, где {element} - JSON-строка)";
    }
}
