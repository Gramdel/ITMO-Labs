package commands;

import static core.Main.collection;

public class Update extends Command{
    public Update(){
        super(true);
    }
    @Override
    public void execute(String arg) {
        if (rightArg(arg)){
            RemoveById removeById = new RemoveById();
            removeById.isCalledByUpdater();
            int i = collection.size();
            removeById.execute(arg);
            if (collection.size()<i){
                Add add = new Add();
                add.isCalledByUpdater(Long.parseLong(arg));
                add.execute("");
                System.out.println("Элемент с id "+arg+" успешно обновлён!");
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
}
