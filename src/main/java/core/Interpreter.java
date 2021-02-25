package core;

import commands.*;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
    public static InputStream stream = System.in;
    private HashMap<Command, String> commands = new HashMap<>();
    private LinkedList<String> history = new LinkedList<>();

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
        commands.put(new RemoveByUOM(), "remove_any_by_unit_of_measure");
        commands.put(new FilterByManufacturer(), "filter_by_manufacturer");
        commands.put(new Save(), "save");
        commands.put(new AddIfMax(), "add_if_max");
        commands.put(new PrintPrice(), "print_field_descending_price");
    }

    public void fromStream(InputStream stream) {
        InputStream prevStream = Interpreter.stream;
        Interpreter.stream = stream;

        Scanner in = new Scanner(stream);

        while (in.hasNext()) {
            String s = in.nextLine();

            if (!s.matches("\\s*")) {
                String com;
                String[] args;

                if (!stream.equals(System.in) && s.matches("\\s*\\w+\\s+(\\d+\\s+)?\\{ *\"[^\"\\r\\n]*\" *: *\"[^\"\\r\\n]*\"( *, *\"[^\"\\r\\n]*\" *: *\"[^\"\\r\\n]*\"){10} *\\}")) {
                    Matcher m = Pattern.compile("[^\\s]+").matcher(s);
                    m.find();
                    com = m.group();
                    m.find();
                    s = s.replaceFirst("\\s*\\w+\\s+","");
                    if(m.group().matches("\\d+")){
                        args = s.split("\\s", 2);
                    } else {
                        args = new String[1];
                        args[0] = s;
                    }
                } else if (!stream.equals(System.in) && s.matches("\\s*filter_by_manufacturer\\s+\\{ *\"[^\"\\r\\n]*\" *: *\"[^\"\\r\\n]*\"( *, *\"[^\"\\r\\n]*\" *: *\"[^\"\\r\\n]*\"){3} *\\}")) {
                    com = "filter_by_manufacturer";
                    args = new String[1];
                    args[0] = s.replaceFirst("\\s*filter_by_manufacturer\\s+", "");
                } else {
                    ArrayList<String> parts = new ArrayList<>();
                    Matcher m = Pattern.compile("[^\\s]+").matcher(s);
                    while (m.find()) parts.add(m.group());

                    com = parts.remove(0);
                    args = new String[parts.size()];
                    parts.toArray(args);
                }

                boolean script = com.equals("execute_script");

                if (commands.containsValue(com)) {
                    for (Map.Entry<Command, String> entry : commands.entrySet()) {
                        if (entry.getValue().equals(com)) {
                            if (script) addToHistory(com);
                            entry.getKey().execute(args);
                            break;
                        }
                    }
                } else {
                    System.out.println("Такой команды не существует! Список команд: help");
                }

                if (!script) addToHistory(com);
            }
        }

        in.close();
        Interpreter.stream = prevStream;
    }

    public HashMap<Command, String> getCommands() {
        return commands;
    }

    public LinkedList<String> getHistory() {
        return history;
    }

    private void addToHistory(String com) {
        history.add(com);
        if (history.size() > 7) history.remove();
    }
}
