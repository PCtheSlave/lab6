package server.commands;

import lib.Pack;
import server.CollectionManager;
import server.interfaces.Command;
import lib.utils.CollectionInfo;

public class Info implements Command {
    private CollectionManager collectionManager;

    private CollectionInfo textFormatter;

    public Info(CollectionManager collectionManager) {

        this.collectionManager = collectionManager;
        textFormatter = new CollectionInfo();
    }

    @Override
    public Pack execute(Pack pack) {
        String response = "";
        try {
            response += textFormatter.getInfoAboutCollection(collectionManager);
        } catch (NullPointerException ex) {
            response += "В коллекции нет элементов";
        }
        pack.pack(response + "\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
