package core;

import commands.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;

public class Interpreter {
    private HashMap<Command, String> commands = new HashMap<>();
    private LinkedList<String> history = new LinkedList<>();

    public Interpreter() {
        commands.put(new Add(), "add");
        commands.put(new Clear(), "clear");
        commands.put(new Exit(), "exit");
        commands.put(new History(), "history");
        commands.put(new Help(), "help");
        commands.put(new ExecuteScript(), "execute_script");
    }

    public void fromStream(InputStream stream) {
        Scanner in = new Scanner(stream);

        while (in.hasNext()) {
            String s = in.nextLine();

            if (!Pattern.matches("\\s*", s)) {
                ArrayList<String> parts = new ArrayList<>();

                Matcher m = Pattern.compile("[^\\s]+").matcher(s);
                while (m.find()) {
                    parts.add(m.group());
                }

                String com = parts.get(0);
                String arg = "";
                if (parts.size() > 1) arg = parts.size() > 2 ? null : parts.get(1);

                history.add(com);
                if (history.size() > 7) history.remove();

                if (commands.containsValue(com)) {
                    for (Map.Entry<Command, String> entry : commands.entrySet()) {
                        if (entry.getValue().equals(com)) {
                            if (com.equals("execute_script") && !stream.equals(System.in)) {
                                ((ExecuteScript) entry.getKey()).setInner();
                            }
                            entry.getKey().execute(arg);
                            break;
                        }
                    }
                } else {
                    System.out.println("Такой команды не существует! Список команд: help");
                }
            }
        }
    }

    public HashMap<Command, String> getCommands() {
        return commands;
    }

    public LinkedList<String> getHistory() {
        return history;
    }
}
