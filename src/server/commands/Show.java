package server.commands;

import lib.Pack;
import lib.dragon.Dragon;
import server.CollectionManager;
import server.interfaces.Command;
import lib.utils.CollectionInfo;

import java.net.Socket;
import java.nio.channels.SocketChannel;

public class Show implements Command {

    private CollectionManager collectionManager;
    private CollectionInfo collectionInfo;
    SocketChannel client;

    public Show(CollectionManager collectionManager, SocketChannel client) {
        this.collectionManager = collectionManager;
        collectionInfo = new CollectionInfo();
        this.client = client;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        String response = "";
        response = collectionInfo.show(collectionManager, client);
        pack.pack(response);
        return pack;
    }

    @Override
    public String getDescription() {
        return "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
