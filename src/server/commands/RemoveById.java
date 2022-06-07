package server.commands;

import lib.Pack;
import lib.dragon.Dragon;
import server.CollectionManager;
import server.interfaces.CommandWithArguments;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RemoveById implements CommandWithArguments {

    private CollectionManager collectionManager;
    private Scanner in;
    private String[] arguments;

    RemoveById (CollectionManager collectionManager, Scanner in) {
        this.collectionManager = collectionManager;
        this.in = in;
    }

    @Override
    public Pack execute(Pack pack) {
        try {
            Set<Dragon> d = new HashSet<>();
            for (Dragon val : collectionManager.getDragons()) {
                for (String argument : arguments) {
                    if (val.getId() == Integer.parseInt(argument)) {
                        d.add(val);
                    }
                }
            }
            d.forEach(dragon -> collectionManager.getDragons().remove(dragon));
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Не указаны все аргументы команды!");
        } catch (NumberFormatException e1) {
            System.err.println("Формат аргумента не соответствует целочисленному ");
        } catch (NullPointerException e2) {
            System.err.println("Введите корректный агрумент(int)");
        }
        pack.pack("Объект по id удалён\n");
        return pack;
    }

    @Override
    public String getDescription() {
        return "Удалить элемент из коллекции по его id";
    }

    @Override
    public void getArguments(String[] arguments) {
        this.arguments = arguments;
    }
}
