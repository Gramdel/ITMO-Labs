package core;

import commands.*;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
    private HashMap<Command, String> commands = new HashMap<>();
    private LinkedList<String> history = new LinkedList<>();
    private InputStream stream;

    public Interpreter() {
        commands.put(new Add(), "add");
        commands.put(new Clear(), "clear");
        commands.put(new Exit(), "exit");
        commands.put(new History(), "history");
        commands.put(new Help(), "help");
        commands.put(new ExecuteScript(), "execute_script");
        commands.put(new Show(), "show");
        commands.put(new RemoveById(), "remove_by_id");
        commands.put(new Update(), "update");
        commands.put(new Info(), "info");
    }

    public void fromStream(InputStream stream) {
        InputStream prevStream = this.stream;
        this.stream = stream;

        Scanner in = new Scanner(stream);

        while (in.hasNext()) {
            String s = in.nextLine();

            if (!s.matches("\\s*")) {
                ArrayList<String> parts = new ArrayList<>();

                Matcher m = Pattern.compile("[^\\s]+").matcher(s);
                while (m.find()) {
                    parts.add(m.group());
                }

                String com = parts.get(0);
                String arg = "";
                if (parts.size() > 1) arg = parts.size() > 2 ? null : parts.get(1);

                boolean script = com.equals("execute_script");

                if (commands.containsValue(com)) {
                    for (Map.Entry<Command, String> entry : commands.entrySet()) {
                        if (entry.getValue().equals(com)) {
                            if (script) addToHistory(com);
                            entry.getKey().execute(arg);
                            break;
                        }
                    }
                } else {
                    System.out.println("Такой команды не существует! Список команд: help");
                }

                if(!script) addToHistory(com);
            }
        }

        in.close();
        this.stream = prevStream;
    }

    public HashMap<Command, String> getCommands() {
        return commands;
    }

    public LinkedList<String> getHistory() {
        return history;
    }

    private void addToHistory(String com){
        history.add(com);
        if (history.size() > 7) history.remove();
    }

    public InputStream getStream() {
        return stream;
    }
}
