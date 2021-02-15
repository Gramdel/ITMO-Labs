package commands;

public class Exit extends Command{
    public Exit(){
        super(false);
    }
    @Override
    public void execute(String arg) {
        System.out.println("Комманда exit выполнена, программа завершает работу.");
    }
}
