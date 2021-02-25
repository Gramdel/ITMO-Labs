package commands;

import collection.Product;
import static core.Main.collection;
import java.util.Iterator;

public class RemoveById extends Command {
    private Product deletedElement;
    private boolean calledByUpdater;

    public RemoveById() {
        super(true);
    }

    @Override
    public void execute(String[] args) {
        if (rightArg(args)) {
            boolean idNotExists = true;
            for (Iterator<Product> iter = collection.iterator(); iter.hasNext(); ) {
                Product i = iter.next();
                if (args[0].equals(Long.toString(i.getId()))) {
                    deletedElement = i;
                    iter.remove();
                    idNotExists = false;
                    if (!calledByUpdater) System.out.println("Элемент с id "+args[0]+" успешно удалён!");
                    break;
                }
            }
            if (idNotExists) System.out.println("В коллекции нет элемента с id "+args[0]+"!");
        }
    }

    @Override
    public String description() {
        return "Удаляет элемент из коллекции по его id." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: remove_by_id id, где id - целое положительное число.";
    }

    @Override
    protected boolean rightArg(String[] args) {
        if (super.rightArg(args)) {
            try {
                Long.parseLong(args[0]);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Неправильный ввод id! Требуемый формат: положительное целое число.");
            }
        }
        return false;
    }

    public void isCalledByUpdater() {
        calledByUpdater = true;
    }

    public Product getDeletedElement() {
        return deletedElement;
    }
}
