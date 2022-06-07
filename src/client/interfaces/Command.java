package client.interfaces;

import lib.Pack;

public interface Command {
    public Pack execute(String nameCommand, Pack pack);
}
