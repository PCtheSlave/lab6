package server.commands;

import lib.Pack;
import lib.dragon.Dragon;
import server.CollectionManager;
import server.interfaces.CommandWithArguments;

import java.util.Scanner;

public class FilterByWeight implements CommandWithArguments {

    private CollectionManager collectionManager;
    private Scanner in;
    private String[] arguments;

    FilterByWeight(CollectionManager collectionManager, Scanner in) {
        this.collectionManager = collectionManager;
        this.in = in;
    }

    @Override
    public Pack execute(Pack pack) {
        String response = "";
        try {
            for (Dragon val : collectionManager.getDragons()) {
                if (val.getWeight() == Long.parseLong(arguments[0])) {
                    response += val + "\n";
                }
            }
            if (response.equals(""))
                response += "Нет драконов с введённой массой\n";
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Не указаны все аргументы команды!");
        } catch (NumberFormatException e) {
            System.err.println("Формат аргумента не соответствует целочисленному ");
        } catch (NullPointerException e) {
            System.err.println("Поле не может быть задано пустой строкой");
        }
        pack.pack(response);
        return pack;
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
