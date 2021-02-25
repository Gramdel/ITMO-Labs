package commands;

import collection.Organization;
import collection.OrganizationType;
import collection.Product;
import org.json.simple.parser.ParseException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.Stack;
import static core.IOUnit.parseJson;
import static core.Main.collection;
import static core.Main.interpreter;

public class FilterByManufacturer extends Command {
    private boolean autoMode = false;
    private Stack<String> errors = new Stack<>();

    private String name;
    private Long annualTurnover;
    private Long employeesCount;
    private OrganizationType type;

    public FilterByManufacturer() {
        super(false);
    }

    @Override
    public void execute(String[] args) {
        hasArgs = !interpreter.stream.equals(System.in);
        if (autoMode) {
            if (checkName(args[0]) & checkAnnualTurnover(args[1]) & checkEmployeesCount(args[2]) & checkType(args[3])) {
                findOrganization();
            } else {
                System.out.println("При описании компании-производителя возникли следующие ошибки ввода:");
                for (String error : errors)
                    System.out.println("\t" + error);
                errors.clear();
            }
            autoMode = false;
        } else if (rightArg(args)) {
            if (args.length == 1) {
                try {
                    FilterByManufacturer filter = new FilterByManufacturer();
                    filter.setAutoMode();
                    filter.execute(parseJson(args[0]));
                } catch (ParseException e) {
                    System.out.println("В структуре JSON-строки, описывающей компанию-производителя, возникла ошибка.");
                }
            } else {
                fieldSetter("Введите название компании-производителя:", "checkName");
                fieldSetter("Введите годовой оборот компании-производителя (пустая строка или целое положительное число):", "checkAnnualTurnover");
                fieldSetter("Введите количество сотрудников компании-производителя (пустая строка или целое положительное число):", "checkEmployeesCount");
                fieldSetter("Введите тип компании-производителя (пустая строка, " + OrganizationType.valueList() + "):", "checkType");

                findOrganization();
            }
        }
    }

    @Override
    public String description() {
        return "Выводит элементы коллекции с manufacturer эквивалентным заданному." + syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: filter_by_manufacturer \n\t\t(В скриптах - filter_by_manufacturer {element}, где {element} - JSON-строка)";
    }

    private void findOrganization() {
        Stack<Product> elements = new Stack<>();
        Organization m2 = new Organization(name, annualTurnover, employeesCount, type);

        for (Product product : collection) {
            Organization m1 = product.getManufacturer();
            if (m1.equals(m2)) {
                elements.push(product);
            }
        }

        if (!elements.isEmpty()) {
            System.out.println("Элементы коллекции с таким manufacturer:");
            elements.sort(Product.byIdComparator);
            for (Product product : elements)
                System.out.println(product);
        } else {
            System.out.println("В коллекции нет ни одного элемента с manufacturer " + m2 + "!");
        }
    }

    private void fieldSetter(String message, String methodName) {
        Scanner in = new Scanner(System.in);
        try {
            Method method = this.getClass().getDeclaredMethod(methodName, String.class);

            do {
                System.out.println(message);
                if (errors.size() > 0) System.out.println(errors.peek());
                errors.clear();
            } while (!(Boolean) method.invoke(this, in.nextLine()));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Товарищ программист, что-то не так с методом " + methodName + "!");
        }
    }

    private boolean checkName(String name) {
        if (name.matches("\\s*")) {
            errors.push("Неправильный ввод названия компании-производителя! Оно не может быть пустой строкой.");
            return false;
        }
        this.name = name;
        return true;
    }

    private boolean checkAnnualTurnover(String annualTurnover) {
        if (!annualTurnover.equals("")) {
            try {
                this.annualTurnover = Long.parseLong(annualTurnover);
            } catch (NumberFormatException e) {
                errors.push("Неправильный ввод ежегодного оборота компании-производителя! Требуемый формат: пустая строка или целое положительное число.");
                return false;
            }
        } else {
            this.annualTurnover = null;
        }
        return true;
    }

    private boolean checkEmployeesCount(String employeesCount) {
        if (!employeesCount.equals("")) {
            try {
                this.employeesCount = Long.parseLong(employeesCount);
            } catch (NumberFormatException e) {
                errors.push("Неправильный ввод количества сотрудников компании-производителя! Требуемый формат: пустая строка или целое положительное число.");
                return false;
            }
        } else {
            this.employeesCount = null;
        }
        return true;
    }

    private boolean checkType(String type) {
        if (!type.equals("")) {
            try {
                this.type = OrganizationType.fromString(type);
            } catch (IllegalArgumentException e) {
                String s = "Неправильный ввод типа компании-производителя! Возможные варианты ввода: пустая строка, " + OrganizationType.valueList() + ".";
                errors.push(s);
                return false;
            }
        } else {
            this.type = null;
        }
        return true;
    }

    public void setAutoMode() {
        autoMode = true;
    }
}