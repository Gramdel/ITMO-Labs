package commands;

import static core.Main.*;

public class Clear extends Command {
    public Clear(){
        super(false);
    }
    @Override
    public void execute(String arg) {
        collection.clear();
        System.out.println("Комманда clear выполнена, коллекция очищена.");
    }
}
