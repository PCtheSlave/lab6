package client.command;

import lib.Pack;
import client.interfaces.Command;

public class CommandWithoutArg implements Command {

    @Override
    public Pack execute(String nameCommand, Pack pack) {
        pack.pack(nameCommand);
        return pack;
    }
}
