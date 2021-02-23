package commands;

import collection.Product;
import java.util.Iterator;
import static core.Main.collection;

public class RemoveById extends Command {
    private boolean calledByUpdater;

    public RemoveById(){
        super(true);
    }

    @Override
    public void execute(String arg) {
        if (rightArg(arg)){
            boolean idNotExists = true;
            for (Iterator<Product> iter = collection.iterator(); iter.hasNext(); )
            {
                if (arg.equals(Long.toString(iter.next().getId())))
                {
                    iter.remove();
                    idNotExists = false;
                    if (!calledByUpdater) System.out.println("Элемент с id "+arg+" успешно удалён!");
                    break;
                }
            }
            if(idNotExists) System.out.println("В коллекции нет элемента с id "+arg+".");
        }
    }

    @Override
    public String description() {
        return "Удаляет элемент из коллекции по его id."+syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: remove_by_id id";
    }

    @Override
    protected boolean rightArg(String arg) {
        if (super.rightArg(arg)) {
            try {
                Long.parseLong(arg);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Неправильный ввод id! Требуемый формат: положительное целое число.");
            }
        }
        return false;
    }

    public void isCalledByUpdater(){
        calledByUpdater = true;
    }
}
