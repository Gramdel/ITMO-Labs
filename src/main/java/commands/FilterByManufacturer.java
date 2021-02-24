package commands;

import collection.Organization;
import collection.Product;
import java.util.Iterator;
import static core.Main.collection;
import static core.Main.interpreter;

public class FilterByManufacturer extends Command {
    private Organization m2;

    public FilterByManufacturer(){
        super(!interpreter.stream.equals(System.in));
    }

    @Override
    public void execute(String[] args) {
        if (rightArg(args)){
            for (Iterator<Product> iter = collection.iterator(); iter.hasNext(); ){
                Product product = iter.next();
                Organization m1 = product.getManufacturer();
                if (m1.equals(m2)) {
                    System.out.println(product);
                }
            }
        }
    }

    @Override
    public String description() {
        return "Выводит элементы коллекции с manufacturer эквивалентным заданному."+syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: filter_by_manufacturer";
    }
}