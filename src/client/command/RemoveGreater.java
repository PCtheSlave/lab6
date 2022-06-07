package client.command;

import client.util.DragonReader;
import lib.Pack;
import client.interfaces.Command;

public class RemoveGreater implements Command {
    @Override
    public Pack execute(String nameCommand, Pack pack) {
        DragonReader dragonReader = new DragonReader();
        pack.pack(nameCommand, dragonReader.createDragon());
        return pack;
    }
}
