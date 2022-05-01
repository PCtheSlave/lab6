package server.commands;

import server.CollectionManager;
import server.interfaces.Command;

public class Shuffle implements Command {

    CollectionManager collectionManager;

    Shuffle(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.randDragon();
    }

    @Override
    public String getDescription() {
        return "Перемешать объекты коллекции";
    }
}
