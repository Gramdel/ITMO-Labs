package core;

import commands.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

import static commands.History.history;

public class Interpreter {

    private static void executor(String com,String arg) {
        if (Main.commands.containsValue(com)){
            for (Map.Entry<Command,String> entry : Main.commands.entrySet()) {
                if (entry.getValue().equals(com)) {
                    entry.getKey().execute(arg);
                }
            }
        } else {
            System.out.println("Такой команды не существует! Список команд: help");
        }
    }

    public static void read(InputStream stream) {
        Scanner in = new Scanner(stream);

        String s;
        while(in.hasNext()) {
            s = in.nextLine();

            if (!s.equals("")) {
                String com = s.split(" ")[0];
                String arg = s.equals(com) ? "" : s.split(" ")[1];
                executor(com, arg);

                history.add(com);
                if (history.size() > 7) history.remove();
                if (history.peekLast().equals("exit")) break;
            }
        }
    }

}
