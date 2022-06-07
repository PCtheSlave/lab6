package client.command;

import client.interfaces.Command;
import client.interfaces.CommandWithArgument;
import lib.Pack;
import lib.utils.IOUtils;

import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommandInvoker {
    private Socket socket;
    public CommandInvoker(Socket socket) {
        this.socket = socket;
        this.commandRegister();
    }

    Map<String, Command> commandWithoutArg = new HashMap<>();
    Map<String, CommandWithArgument> commandWithArgument = new HashMap<>();

    public void commandRegister() {
        register("help", new CommandWithoutArg());
        register("info", new CommandWithoutArg());
        register("show", new CommandWithoutArg());
        register("clear", new CommandWithoutArg());
        register("exit", new CommandWithoutArg());
        register("shuffle", new CommandWithoutArg());
        register("average_of_weight", new CommandWithoutArg());
        register("group_by_age", new CommandWithoutArg());
        register("add", new Add());
        register("remove_greater", new RemoveGreater());
        register("remove_lower", new RemoveLower());
        registerWithArg("update", new Update(socket));
        registerWithArg("remove_by_id", new RemoveById());
        registerWithArg("execute_script", new ExecuteScript(socket));
        registerWithArg("filter_by_weight", new FilterByWeight());
    }

    public void register(String nameCommand, Command command) {
        commandWithoutArg.put(nameCommand, command);
    }

    public void registerWithArg(String nameCommand, CommandWithArgument commandWithArg) {
        commandWithArgument.put(nameCommand, commandWithArg);
    }

    public boolean execute(String str, Pack pack) {
        String[] word = str.trim().split(" ");
        String[] arg = Arrays.copyOfRange(word, 1, word.length);

        if (commandWithArgument.containsKey(word[0].toLowerCase(Locale.ROOT))) {
            if (arg.length == 1) {
                CommandWithArgument command;
                command = commandWithArgument.get(word[0].toLowerCase(Locale.ROOT));
                command.arg(arg);
                command.execute(word[0], pack);
                return true;
            } else {
                IOUtils.printErr("У комманды " + word[0] + " должен быть один аргумент!");
                return false;
            }
        } else if (commandWithoutArg.containsKey(word[0].toLowerCase(Locale.ROOT))) {
            if (arg.length <= 0) {
                Command command;
                command = commandWithoutArg.get(word[0].toLowerCase(Locale.ROOT));
                command.execute(word[0], pack);
                return true;
            } else {
                IOUtils.printErr("У комманды " + word[0] + " не должно быть аргументов!");
                return false;
            }
        } else {
            IOUtils.printErr("Комманда " + word[0] + " не распознана, введите корректную команду!");
            return false;
        }
    }
}
