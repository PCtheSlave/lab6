package server.commands;

import lib.collection.Dragon;
import server.CollectionManager;
import server.interfaces.Command;

public class GroupCountingByAge implements Command {

    CollectionManager collectionManager;

    GroupCountingByAge(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        System.out.println("Молодые драконы:");
        for (Dragon val : collectionManager.getDragons()) {
            if (val.getAge() <= 14) {
                System.out.println(val);
            }
        }
        System.out.println("Средние драконы:");
        for (Dragon val : collectionManager.getDragons()) {
            if (val.getAge() > 14 && val.getAge() < 40) {
                System.out.println(val);
            }
        }
        System.out.println("Старые драконы:");
        for (Dragon val : collectionManager.getDragons()) {
            if (val.getAge() >= 40) {
                System.out.println(val);
            }
        }
    }

    @Override
    public String getDescription() {
        return "Сгруппировать элементы коллекции по значению поля age, вывести количество элементов в каждой группе";
    }
}
