package core;

import commands.*;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class Interpreter {

    private static void executor(String com,String arg) {
        if (Main.commands.containsValue(com)){
            for (Map.Entry entry : Main.commands.entrySet()) {
                if (entry.getValue().equals(com)) {
                    ((Command) entry.getKey()).execute(arg);
                }
            }
        } else {
            System.out.println("Такой команды не существует! Список команд: help");
        }
    }

    public static String read(InputStream stream){
        Scanner in = new Scanner(stream);
        String s = "";
        while(s.equals("")) s = in.nextLine();

        String com = s.split(" ")[0];
        String arg = s.equals(com) ? "" : s.split(" ")[1];

        executor(com,arg);

        return com;
    }
}
