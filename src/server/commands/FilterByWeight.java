package server.commands;

import lib.collection.Dragon;
import server.CollectionManager;
import server.interfaces.CommandWithArguments;

import java.util.Scanner;

public class FilterByWeight implements CommandWithArguments {

    private CollectionManager collectionManager;
    private Scanner in;
    private String[] arguments;

    FilterByWeight (CollectionManager collectionManager, Scanner in) {
        this.collectionManager = collectionManager;
        this.in = in;
    }

    @Override
    public void execute() {
        try {
            for (Dragon val : collectionManager.getDragons()) {
                for (String argument : arguments) {
                    if (val.getWeight() == Long.parseLong(argument)) {
                        System.out.println(val);
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Не указаны все аргументы команды!");
        } catch (NumberFormatException e) {
            System.err.println("Формат аргумента не соответствует целочисленному ");
        } catch (NullPointerException e) {
            System.err.println("Поле не может быть задано пустой строкой");
        }
    }

    @Override
    public String getDescription() {
        return "Вывести элементы, значение поля weight которых равно заданному";
    }

    @Override
    public void getArguments(String[] arguments) {
        this.arguments = arguments;
    }
}
