package commands;

public abstract class Command {
    private boolean isComplex;
    public Command(boolean isComplex){
        this.isComplex = isComplex;
    }
    public abstract void execute(String arg);
}
