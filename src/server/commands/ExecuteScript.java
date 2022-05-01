package server.commands;

import server.interfaces.CommandWithArguments;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScript implements CommandWithArguments {

    Set<String> scriptsInProcess = new HashSet<>();
    Scanner scanner;
    CommandInvoker invoker;
    String[] arguments;

    public ExecuteScript(CommandInvoker invoker, Scanner scanner) {
        this.scanner = scanner;
        this.invoker = invoker;
    }

    @Override
    public void execute() {
        String fileName = arguments[0];
        File file = new File(fileName);
        String absolutePath = file.getAbsolutePath();

        if (scriptsInProcess.contains(absolutePath)) {
            System.err.println("Данный скрипт уже выполняется.");
            System.err.println("Выполнение этого скрипта в данный момент невозможно.");
            return;
        }
        scriptsInProcess.add(absolutePath);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            System.out.printf("Попытка прочитать команды из файла \"%s\":%n", file.getName());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.printf("Команда \"%s\":%n", line);
                invoker.execute(line);
            }
            System.out.println("Все команды были успешно выполнены.");
        } catch (Exception e) {
            System.err.println("Непредвиденная ошибка при выполнении скрипта.");
        }
        scriptsInProcess.remove(absolutePath);
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
