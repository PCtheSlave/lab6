package server.commands;

import server.interfaces.Command;
import server.interfaces.CommandWithArguments;

import java.util.HashMap;
import java.util.Map;

public class Help implements Command {

    private HashMap<String, Command> commandsWithoutArguments;
    private HashMap<String, CommandWithArguments> commandWithArguments;

    public Help(HashMap<String, Command> commandsWithoutArguments, HashMap<String, CommandWithArguments> commandWithArguments) {
        this.commandsWithoutArguments = commandsWithoutArguments;
        this.commandWithArguments = commandWithArguments;
    }

    @Override
    public void execute() {
        for (Map.Entry<String, Command> entry : commandsWithoutArguments.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getDescription());
        }

        for (Map.Entry<String, CommandWithArguments> entry : commandWithArguments.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "Вывести справку по доступным командам";
    }
}
