package server.commands;

import lib.Pack;
import lib.dragon.Dragon;
import server.CollectionManager;
import server.interfaces.Command;

public class GroupByAge implements Command {

    CollectionManager collectionManager;

    GroupByAge(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        String response = "";
        response = "Молодые драконы:\n";
        for (Dragon val : collectionManager.getDragons()) {
            if (val.getAge() <= 14) {
                response += val + "\n";
            }
        }
        response += "Средние драконы:\n";
        for (Dragon val : collectionManager.getDragons()) {
            if (val.getAge() > 14 && val.getAge() < 40) {
                response += val + "\n";
            }
        }
        response += "Старые драконы:\n";
        for (Dragon val : collectionManager.getDragons()) {
            if (val.getAge() >= 40) {
                response += val + "\n";
            }
        }
        pack.pack(response);
        return pack;
    }

    @Override
    public String getDescription() {
        return "Сгруппировать элементы коллекции по значению поля age, вывести количество элементов в каждой группе";
    }
}
