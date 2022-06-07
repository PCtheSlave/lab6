package client.command;

import lib.Pack;
import client.interfaces.CommandWithArgument;

public class RemoveById implements CommandWithArgument {
    private String[] arg;
    @Override
    public Pack execute(String nameCommand, Pack pack) {
        pack.pack(nameCommand, arg);
        return pack;
    }

    @Override
    public void arg(String[] arg) {
        this.arg = arg;
    }
}
