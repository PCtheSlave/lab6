package server.commands;

import lib.Pack;
import server.CollectionManager;
import server.interfaces.CommandWithArguments;

public class GetDragon implements CommandWithArguments {
    CollectionManager collectionManager;
    String[] arg = {""};

    GetDragon(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        collectionManager.reload(pack.getDragon());
        return pack;
    }

    @Override
    public String getDescription() {
        return "getDragon";
    }

    @Override
    public void getArguments(String[] arguments) {
        this.arg = arguments;
    }
}
