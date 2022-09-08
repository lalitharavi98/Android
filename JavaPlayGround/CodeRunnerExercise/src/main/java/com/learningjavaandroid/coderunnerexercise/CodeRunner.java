package com.learningjavaandroid.coderunnerexercise;

public class CodeRunner {
    public static void main(String[] args) {
        int command = 2;
        System.out.println(running(command));

    }
    public static String running(int command) {
        if(command == 0) {
            return "CodeRunner running North";
        } else if (command == 1) {
            return "CodeRunner running South";
        } else if ( command == 2) {
            return "CodeRunner running West";
        } else if ( command == 3) {
            return "CodeRunner running East";
        } else if ( (command >=4) || (command < 0) ) {
            return "CodeRunner is confused - doesn't know where to go!";
        } else {
            return "Unknown command.";
        }

    }
}