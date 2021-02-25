package commands;

import collection.Product;
import org.json.simple.parser.ParseException;

import static core.IOUnit.parseJson;
import static core.Main.collection;
import static core.Interpreter.stream;

public class Update extends Command {
    public Update() {
        super(true);
    }

    @Override
    public void execute(String[] args) {
        if (rightArg(args)) {
            int prevSize = collection.size();

            RemoveById removeById = new RemoveById();
            removeById.isCalledByUpdater();
            removeById.execute(args);

            Product deletedElement = removeById.getDeletedElement();

            if (collection.size() < prevSize) {
                prevSize = collection.size();
                Add add = new Add();
                add.isCalledByUpdater();
                add.setId(Long.parseLong(args[0]));
                if (!stream.equals(System.in)) {
                    try {
                        add.setAutoMode();
                        add.execute(parseJson(args[1]));
                    } catch (ParseException e) {
                        System.out.println("Элемент c id "+args[0]+" не обновлён из-за ошибки в структуре JSON-строки.");
                    }
                } else {
                    add.execute(new String[0]);
                }
                if (collection.size() == prevSize) {
                    collection.add(deletedElement);
                }
            }
        }
    }

    @Override
    public String description() {
        return "Обновляет значение элемента коллекции, id которого равен заданному." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: update id, где id - целое положительное число. \n\t\t(В скриптах - update id {element}, где {element} - JSON-строка)";
    }

    @Override
    protected boolean rightArg(String[] args) {
        if (super.rightArg(args)) {
            try {
                Long.parseLong(args[0]);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Неправильный ввод id! Требуемый формат: целое положительное число.");
            }
        }
        return false;
    }
}
