package server.commands;

import lib.Pack;
import server.CollectionManager;
import server.interfaces.Command;

public class Add implements Command {
    private CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        collectionManager.insert(pack.getDragon());
        pack.pack("Дракон добавлен\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Добавить новый элемент в коллекцию";
    }
}
