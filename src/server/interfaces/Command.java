package server.interfaces;


import lib.Pack;

public interface Command {
    Pack execute(Pack pack);
    String getDescription();
}
