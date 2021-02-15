package core;

import commands.Command;
import java.util.Scanner;

public class Interpreter {
    public static String read(){
        Scanner in = new Scanner(System.in);
        String s = "";
        while(s.equals("")) s = in.nextLine();

        String com = s.split(" ")[0];
        String arg = s.equals(com) ? "" : s.split(" ")[1];
        s = com;

        try{
            if (Character.isLowerCase(com.charAt(0))){
                com = com.substring(0,1).toUpperCase()+com.substring(1);
                ((Command) Class.forName("commands."+com).newInstance()).execute(arg);
            } else {
                throw new ClassNotFoundException();
            }
        } catch(ClassNotFoundException | InstantiationException | IllegalAccessException | NoClassDefFoundError e) {
            //e.printStackTrace();
            System.out.println("Некорректный ввод, такой команды не существует!\n" +
                    "Список команд: help");
        }
        return s;
    }
}
