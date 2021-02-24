package commands;

import collection.Product;
import collection.UnitOfMeasure;
import static core.Main.collection;
import java.util.Iterator;

public class RemoveByUOM extends Command {
    public RemoveByUOM() {
        super(true);
    }

    @Override
    public void execute(String[] args) {
        if (rightArg(args)) {
            int prevSize = collection.size();

            for (Iterator<Product> iter = collection.iterator(); iter.hasNext(); ) {
                if (args[0].equals(iter.next().getUnitOfMeasure().toString())) {
                    iter.remove();
                    break;
                }
            }

            if (collection.size() < prevSize) {
                System.out.println("Из коллекции удалён один из элементов с unitOfMeasure " + args[0] + ".");
            } else {
                System.out.println("В коллекции нет ни одного элемента с unitOfMeasure " + args[0] + "!");
            }
        }
    }

    @Override
    public String description() {
        return "Удаляет из коллекции один из элементов с unitOfMeasure эквивалентным заданному." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: remove_any_by_unit_of_measure unitOfMeasure, где unitOfMeasure: " + UnitOfMeasure.valueList() + ".";
    }

    @Override
    protected boolean rightArg(String[] args) {
        if (super.rightArg(args)) {
            try {
                UnitOfMeasure.fromString(args[0]);
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println("Неправильный ввод единиц измерения! Возможные варианты ввода: " + UnitOfMeasure.valueList() + ".");
            }
        }
        return false;
    }
}
