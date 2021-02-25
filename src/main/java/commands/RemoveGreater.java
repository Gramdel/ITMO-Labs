package commands;

import collection.Product;
import core.Interpreter;
import java.util.ArrayList;
import static core.Main.collection;

public class RemoveGreater extends Command{
    public RemoveGreater() {
        super(false);
    }

    @Override
    public void execute(String[] args) {
        hasArgs = !Interpreter.stream.equals(System.in);
        if (rightArg(args)) {
            if (collection.size() > 0) {
                int prevSize = collection.size();
                Add add = new Add();
                add.isCalledByRemover();
                add.execute(args);

                if(collection.size()>prevSize) {
                    ArrayList<Product> sortedCollection = new ArrayList<>(collection);
                    sortedCollection.sort(Product.byIdComparator);
                    Product product = sortedCollection.get(sortedCollection.size() - 1);

                    RemoveById removeById = new RemoveById();
                    removeById.isCalledByUpdater();
                    for (int i = 0; i < sortedCollection.size() - 1; i++) {
                        if (Product.byPriceComparator.compare(sortedCollection.get(i), product) > 0) {
                            String[] arg = {sortedCollection.get(i).getId().toString()};
                            removeById.execute(arg);
                        }
                    }
                    String[] arg = {product.getId().toString()};
                    removeById.execute(arg);
                    if (prevSize > collection.size()) {
                        System.out.println("Элементы успешно удалены!");
                    } else {
                        System.out.println("Ничего не удалено.");
                    }
                }
            } else {
                System.out.println("Коллекция пуста!");
            }
        }
    }

    @Override
    public String description() {
        return "Добавляет новый элемент в коллекцию, если его цена превышает наибольшую цену элемента в коллекции." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: remove_greater \n\t\t(В скриптах - remove_greater {element}, где {element} - JSON-строка)";
    }
}
