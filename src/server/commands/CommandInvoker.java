package server.commands;

import lib.Pack;
import server.CollectionManager;
import server.interfaces.Command;
import server.interfaces.CommandWithArguments;
import lib.utils.DragonFieldsReader;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class CommandInvoker {
    Scanner in;
    private HashMap<String, Command> commandWithoutArguments;
    private HashMap<String, CommandWithArguments> commandWithArguments;
    private CollectionManager collectionManager;
    DragonFieldsReader dragonFieldsReader;
    SocketChannel client;

    public CommandInvoker(CollectionManager collectionManager, Scanner in, DragonFieldsReader dragonFieldsReader, SocketChannel client) {
        this.collectionManager = collectionManager;
        this.in = in;
        this.dragonFieldsReader = dragonFieldsReader;
        commandWithoutArguments = new HashMap<>();
        commandWithArguments = new HashMap<>();
        this.client = client;
        this.registerCommands();
    }


    private void registerCommands() {
        register("help", new Help(commandWithoutArguments, commandWithArguments));//
        register("info", new Info(collectionManager));//
        register("show", new Show(collectionManager, client));//
        register("add", new Add(collectionManager));//
        registerWithArgument("update", new Update(collectionManager));//
        registerWithArgument("remove_by_id", new RemoveById(collectionManager, in));//
        register("clear", new Clear(collectionManager));//
        registerWithArgument("execute_script", new ExecuteScript());
        register("exit", new Exit(collectionManager));//
        register("shuffle", new Shuffle(collectionManager));//
        register("remove_greater", new RemoveGreater(collectionManager));//
        register("remove_lower", new RemoveLower(collectionManager));//
        register("average_of_weight", new AverageOfWeight(collectionManager));//
        register("group_by_age", new GroupByAge(collectionManager));//
        registerWithArgument("filter_by_weight", new FilterByWeight(collectionManager, in));//
        registerWithArgument("get_dragon", new GetDragon(collectionManager));
    }

    private void register(String name, Command command) {
        commandWithoutArguments.put(name, command);
    }
    private void registerWithArgument(String name, CommandWithArguments command) {
        commandWithArguments.put(name, command);
    }

    public Pack execute(Pack pack) {
        String nameCommand = pack.getCommandName();
        String[] args = pack.getArg();
        Pack response = new Pack();
        if (commandWithArguments.containsKey(nameCommand.toLowerCase(Locale.ROOT))) {
            CommandWithArguments command;
            command = commandWithArguments.get(nameCommand.toLowerCase(Locale.ROOT));
            command.getArguments(args);
            response = command.execute(pack);
        } else if (commandWithoutArguments.containsKey(nameCommand.toLowerCase(Locale.ROOT))) {
            Command command;
            command = commandWithoutArguments.get(nameCommand.toLowerCase(Locale.ROOT));
            response = command.execute(pack);
        }
        return response;
    }
}
