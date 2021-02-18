package commands;

public abstract class Command {
    private boolean hasArg;
    public Command(boolean hasArg){
        this.hasArg = hasArg;
    }
    public abstract void execute(String arg);
    public abstract String description();
    public abstract String syntax();
    protected boolean rightArg(String arg){
        boolean a = arg != null && hasArg ^ arg.equals("");
        if(!a) {
            if (hasArg) System.out.println("У этой комманды должен быть один аргумент."+syntax());
            else System.out.println("У этой комманды не может быть аргументов."+syntax());
        }
        return a;
    }
}
