package commands;

public abstract class Command {
    private boolean hasArgs;
    public Command(boolean hasArgs){
        this.hasArgs = hasArgs;
    }
    public abstract void execute(String[] args);
    public abstract String description();
    public abstract String syntax();
    protected boolean rightArg(String[] args){
        boolean a = hasArgs ^ (args.length == 0);
        if(!a) {
            if (hasArgs) System.out.println("У этой комманды должен быть один аргумент."+syntax());
            else System.out.println("У этой комманды не может быть аргументов."+syntax());
        }
        return a;
    }
}
