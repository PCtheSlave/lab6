package server.commands;

import lib.Pack;
import server.CollectionManager;
import server.interfaces.Command;

public class Clear implements Command {

    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        String response = "";
        collectionManager.clear();
        pack.pack("Коллекция очищена!\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Очистить коллекцию";
    }
}
