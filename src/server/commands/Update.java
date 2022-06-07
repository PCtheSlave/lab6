package server.commands;

import lib.Pack;
import server.CollectionManager;
import server.interfaces.CommandWithArguments;
import lib.utils.CollectionInfo;

import java.util.Scanner;

public class Update implements CommandWithArguments {
    private String[] arguments;
    private CollectionManager collectionManager;
    private Scanner in;
    private CollectionInfo collectionInfo;

    public Update(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        collectionInfo = new CollectionInfo();
    }

    @Override
    public Pack execute(Pack pack) {
        if (collectionManager.getElementById(Long.parseLong(arguments[0])) != null) {
            pack.pack("", collectionManager.getElementById(Long.parseLong(arguments[0])));
        }
        else
            pack.pack("Элемента с данным id нет в коллекции!");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public void getArguments(String[] arguments) {
        this.arguments = arguments;
    }
}
