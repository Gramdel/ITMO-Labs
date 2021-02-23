package commands;

import collection.*;

import static core.Main.collection;
import static core.Main.organizations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.Stack;

public class Add extends Command {
    private boolean calledByUpdater;
    private boolean autoMode = false;
    private Stack<String> errors = new Stack<>();

    private Long id;
    private String name;
    private Double x; //coordinates
    private Long y; //coordinates
    private float price;
    private String partNumber;
    private Float manufactureCost;
    private UnitOfMeasure unitOfMeasure;
    private String name2; //manufacturer
    private Long annualTurnover; //manufacturer
    private Long employeesCount; //manufacturer
    private OrganizationType type; //manufacturer

    public Add(){
        super(false);
    }

    @Override
    public void execute(String arg) {
        if (autoMode){
            if (arg.matches("[^,]*(,[^,]*){10}")) {
                String[] args = arg.split("[,;]", -1);

                if (checkName(args[0]) & checkCoordinateX(args[1]) & checkCoordinateY(args[2]) &
                        checkPrice(args[3]) & checkPartNumber(args[4]) & checkManufactureCost(args[5]) &
                        checkUnitOfMeasure(args[6]) & checkName2(args[7]) & checkAnnualTurnover(args[8]) &
                        checkEmployeesCount(args[9]) & checkType(args[10])) {

                    collection.add(new Product(name, new Coordinates(x, y), price, partNumber, manufactureCost,
                            unitOfMeasure, chooseOrganization()));
                } else {
                    System.out.println("Элемент \"" + arg + "\" не добавлен из-за следующих ошибок ввода:");
                    for (String error : errors)
                        System.out.println("\t" + error);
                }
                errors.clear();
            }
            autoMode = false;
        } else if (rightArg(arg)){

            fieldSetter("Введите название продукта:","checkName");
            fieldSetter("Введите координату x (дробное число):","checkCoordinateX");
            fieldSetter("Введите координату y (целое число):","checkCoordinateY");
            fieldSetter("Введите цену продукта (целое положительное число):","checkPrice");
            fieldSetter("Введите код производителя (#xxxxxx, где x - цифры):","checkPartNumber");
            fieldSetter("Введите цену производства продукта (целое положительное число):","checkManufactureCost");
            String s = "Введите единицу измерения (";
            for (int i = 0; i<UnitOfMeasure.values().length;i++){
                s += UnitOfMeasure.values()[i] + ((i!=UnitOfMeasure.values().length-1) ? ", " : "):");
            }
            fieldSetter(s,"checkUnitOfMeasure");
            fieldSetter("Введите название компании-производителя:","checkName2");
            fieldSetter("Введите годовой оборот компании-производителя (пустая строка или целое положительное число):","checkAnnualTurnover");
            fieldSetter("Введите количество сотрудников компании-производителя (пустая строка или целое положительное число):","checkEmployeesCount");
            s = "Введите тип компании-производителя (пустая строка, ";
            for (int i = 0; i<OrganizationType.values().length;i++){
                s += OrganizationType.values()[i] + ((i!=OrganizationType.values().length-1) ? ", " : "):");
            }
            fieldSetter(s,"checkType");

            Product product = new Product(name, new Coordinates(x, y), price, partNumber, manufactureCost,
                    unitOfMeasure, chooseOrganization());
            product.setId(id);
            collection.add(product);

            if (!calledByUpdater) System.out.println("Элемент добавлен в коллекцию.");
        }
    }

    @Override
    public String description() {
        return "Добавляет новый элемент в коллекцию."+syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: add";
    }

    public void setAutoMode(){
        autoMode = true;
    }

    private Organization chooseOrganization(){
        for (Organization o : organizations){
            if (o.getName().equals(name2) && o.getAnnualTurnover().equals(annualTurnover) &&
                    o.getEmployeesCount().equals(employeesCount) && o.getType().equals(type)) {
                return o;
            }
        }
        Organization organization = new Organization(name2, annualTurnover, employeesCount, type);
        organizations.add(organization);
        return organization;
    }

    private void fieldSetter(String message, String methodName){
        Scanner in = new Scanner(System.in);
        try {
            Method method = this.getClass().getDeclaredMethod(methodName, String.class);

            do {
                System.out.println(message);
                if (errors.size()>0) System.out.println(errors.peek());
                errors.clear();
            } while (!(Boolean) method.invoke(this, in.nextLine()));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            System.out.println("Товарищ программист, что-то не так с методом "+methodName+"!");
        }
    }

    private boolean checkName(String name){
        if (name.matches("\\s*")){
            errors.push("Неправильный ввод названия продукта! Оно не может быть пустой строкой.");
            return false;
        }
        this.name = name;
        return true;
    }

    private boolean checkCoordinateX(String x){
        try {
            this.x = Double.parseDouble(x);
        } catch(NumberFormatException e) {
            errors.push("Неправильный ввод координаты x! Требуемый формат: дробное число.");
            return false;
        }
        return true;
    }

    private boolean checkCoordinateY(String y){
        try {
            this.y = Long.parseLong(y);
        } catch(NumberFormatException e) {
            errors.push("Неправильный ввод координаты y! Требуемый формат: целое число.");
            return false;
        }
        return true;
    }

    private boolean checkPrice(String price){
        try {
            this.price = Float.parseFloat(price);
            if (this.price<=0) throw new NumberFormatException();
        } catch(NumberFormatException e) {
            errors.push("Неправильный ввод цены продукта! Требуемый формат: положительное дробное число.");
            return false;
        }
        return true;
    }

    private boolean checkPartNumber(String partNumber){
        if (partNumber.matches("#\\d{6}")){
            for (Product product : collection){
                if(product.getPartNumber().equals(partNumber)) {
                    errors.push("Неправильный ввод кода производителя! Элемент с таким номером уже есть в коллекции.");
                    return false;
                }
            }
        } else {
            errors.push("Неправильный ввод кода производителя! Требуемый формат: #xxxxxx, где x - цифры.");
            return false;
        }
        this.partNumber = partNumber;
        return true;
    }

    private boolean checkManufactureCost(String manufactureCost){
        try {
            this.manufactureCost = Float.parseFloat(manufactureCost);
            if (this.price<=0) throw new NumberFormatException();
        } catch(NumberFormatException e) {
            errors.push("Неправильный ввод цены производства продукта! Требуемый формат: положительное дробное число.");
            return false;
        }
        return true;
    }

    private boolean checkUnitOfMeasure(String unitOfMeasure){
        try {
            this.unitOfMeasure = UnitOfMeasure.fromString(unitOfMeasure);
        } catch(IllegalArgumentException e) {
            String s = "Неправильный ввод единиц измерения! Возможные варианты ввода: ";
            for (int i = 0; i<UnitOfMeasure.values().length;i++){
                s += UnitOfMeasure.values()[i] + ((i!=UnitOfMeasure.values().length-1) ? ", " : ".");
            }
            errors.push(s);
            return false;
        }
        return true;
    }

    private boolean checkName2(String name2){
        if (name2.matches("\\s*")){
            errors.push("Неправильный ввод названия компании-производителя! Оно не может быть пустой строкой.");
            return false;
        }
        this.name2 = name2;
        return true;
    }

    private boolean checkAnnualTurnover(String annualTurnover){
        if (!annualTurnover.equals("")){
            try {
                this.annualTurnover = Long.parseLong(annualTurnover);
            } catch(NumberFormatException e) {
                errors.push("Неправильный ввод ежегодного оборота компании-производителя! Требуемый формат: пустая строка или целое положительное число.");
                return false;
            }
        } else {
            this.annualTurnover = null;
        }
        return true;
    }

    private boolean checkEmployeesCount(String employeesCount){
        if (!employeesCount.equals("")) {
            try {
                this.employeesCount = Long.parseLong(employeesCount);
            } catch(NumberFormatException e) {
                errors.push("Неправильный ввод количества сотрудников компании-производителя! Требуемый формат: пустая строка или целое положительное число.");
                return false;
            }
        } else {
            this.employeesCount = null;
        }
        return true;
    }

    private boolean checkType(String type){
        if (!type.equals("")) {
            try {
                this.type = OrganizationType.fromString(type);
            } catch (IllegalArgumentException e) {
                String s = "Неправильный ввод типа компании-производителя! Возможные варианты ввода: пустая строка, ";
                for (int i = 0; i<OrganizationType.values().length;i++){
                    s += OrganizationType.values()[i] + ((i!=OrganizationType.values().length-1) ? ", " : ".");
                }
                errors.push(s);
                return false;
            }
        } else {
            this.type = null;
        }
        return true;
    }

    public void isCalledByUpdater(Long id){
        calledByUpdater = true;
        this.id = id;
    }
}