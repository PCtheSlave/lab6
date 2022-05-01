package server.commands;

import lib.collection.DragonFactory;
import server.CollectionManager;
import server.interfaces.Command;

public class Add implements Command {

    private CollectionManager collectionManager;
    DragonFactory dragonFactory = new DragonFactory();

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.insert(dragonFactory.createDragon());
    }

    @Override
    public String getDescription() {
        return "Добавить новый элемент в коллекцию";
    }
}
