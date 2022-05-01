package server.commands;

import lib.collection.Dragon;
import lib.collection.DragonFactory;
import server.CollectionManager;
import server.interfaces.Command;

import java.util.Stack;

public class RemoveGreater implements Command {

    private CollectionManager collectionManager;

    RemoveGreater(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        DragonFactory dragonFactory = new DragonFactory();
        Dragon currentDragon = collectionManager.insert(dragonFactory.createDragon());
        Stack<Dragon> needToRemoveDragons = new Stack<>();
        for (Dragon dragon : collectionManager.getDragons()) {
            if (dragon.compareTo(currentDragon) > 0) {
                needToRemoveDragons.add(dragon);
            }
        }
        collectionManager.getDragons().removeAll(needToRemoveDragons);
    }

    @Override
    public String getDescription() {
        return "Удалить из коллекции все элементы, превышающие заданный";
    }
}
