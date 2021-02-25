package commands;

import collection.Product;
import java.util.ArrayList;
import static core.Main.collection;

public class PrintPrice extends Command {
    public PrintPrice() {
        super(false);
    }

    @Override
    public void execute(String[] args) {
        if (rightArg(args)) {
            if (collection.size() > 0) {
                System.out.println("Цены продуктов в коллекции в порядке убывания:");
                ArrayList<Product> sortedCollection = new ArrayList<>(collection);
                sortedCollection.sort(Product.byPriceComparator.reversed());
                for (Product product : sortedCollection) {
                    System.out.println("\t"+product.getPrice());
                }
            } else {
                System.out.println("Коллекция пуста!");
            }
        }
    }

    @Override
    public String description() {
        return "Выводит цены продуктов в коллекции в порядке убывания." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: print_field_descending_price";
    }
}
