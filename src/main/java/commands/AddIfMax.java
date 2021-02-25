package commands;

import core.Interpreter;
import static core.Main.collection;

public class AddIfMax extends Command{
    public AddIfMax() {
        super(false);
    }

    @Override
    public void execute(String[] args) {
        hasArgs = !Interpreter.stream.equals(System.in);
        if (rightArg(args)) {
            if (collection.size() > 0) {
                Add add = new Add();
                add.isAddIfMax();
                add.execute(args);
            } else {
                System.out.println("Коллекция пуста, поэтому элемент не с чем сравнить!");
            }
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
