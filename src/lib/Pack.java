package lib;

import lib.dragon.Dragon;

import java.io.Serializable;

public class Pack implements Serializable {
    String commandName;
    String[] arg;
    Dragon dragon;

    public void pack(String commandName) {
        this.commandName = commandName;
    }

    public void pack(String commandName, String[] arg) {
        this.commandName = commandName;
        this.arg = arg;
    }

    public void pack(String commandName, Dragon dragon) {
        this.commandName = commandName;
        this.dragon = dragon;
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getArg() {
        return arg;
    }

    public Dragon getDragon() {
        return dragon;
    }


}
