package server.commands;

import lib.Pack;
import lib.utils.DragonFieldsReader;
import server.interfaces.CommandWithArguments;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScript implements CommandWithArguments {

    String[] arguments;

    @Override
    public Pack execute(Pack pack) {
        pack.pack("Скрипт выполнен\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла";
    }

    @Override
    public void getArguments(String[] arguments) {
        this.arguments = arguments;
    }
}
