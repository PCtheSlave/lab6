package server.commands;

import lib.Pack;
import lib.dragon.Dragon;
import server.DragonFactory;
import server.CollectionManager;
import server.interfaces.Command;

import java.util.Stack;

public class RemoveLower implements Command {

    private CollectionManager collectionManager;

    RemoveLower(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        collectionManager.insert(pack.getDragon());
        Stack<Dragon> needToRemoveDragons = new Stack<>();
        for (Dragon dragon : collectionManager.getDragons()) {
            if (dragon.compareTo(pack.getDragon()) < 0) {
                needToRemoveDragons.push(dragon);
            }
        }
        collectionManager.getDragons().removeAll(needToRemoveDragons);
        pack.pack("Объекты превышающие введённый - удалены\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
