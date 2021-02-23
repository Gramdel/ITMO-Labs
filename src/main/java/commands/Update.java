package commands;

import static core.Main.collection;

public class Update extends Command{
    public Update(){
        super(true);
    }
    @Override
    public void execute(String[] args) {
        if (rightArg(args)){
            RemoveById removeById = new RemoveById();
            removeById.isCalledByUpdater();
            int prevSize = collection.size();
            removeById.execute(args);
            if (collection.size()<prevSize){
                Add add = new Add();
                add.isCalledByUpdater(Long.parseLong(args[0]));
                add.execute(new String[0]);
                System.out.println("Элемент с id "+args[0]+" успешно обновлён!");
            }
        }
    }

    @Override
    public String description() {
        return "Обновляет значение элемента коллекции, id которого равен заданному."+syntax();
    }

    @Override
    public String syntax() {
        return " Синтаксис: update id";
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
}
