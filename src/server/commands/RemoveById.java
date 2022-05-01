package server.commands;

import lib.collection.Dragon;
import server.CollectionManager;
import server.interfaces.CommandWithArguments;

import java.util.Scanner;

public class RemoveById implements CommandWithArguments {

    private CollectionManager collectionManager;
    private Scanner in;
    private String[] arguments;

    RemoveById (CollectionManager collectionManager, Scanner in) {
        this.collectionManager = collectionManager;
        this.in = in;
    }

    @Override
    public void execute() {
        try {
            for (Dragon val : collectionManager.getDragons()) {
                for (String argument : arguments) {
                    if (val.getId() == Integer.parseInt(argument)) {
                        collectionManager.removeById(Integer.parseInt(argument));
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Не указаны все аргументы команды!");
        } catch (NumberFormatException e1) {
            System.err.println("Формат аргумента не соответствует целочисленному ");
        } catch (NullPointerException e2) {
            System.err.println("Введите корректный агрумент(int)");
        }
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
