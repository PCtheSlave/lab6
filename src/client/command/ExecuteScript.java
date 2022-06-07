package client.command;

import client.client.Request;
import client.client.Response;
import client.util.DragonReader;
import lib.Pack;
import client.interfaces.CommandWithArgument;
import lib.utils.IOUtils;

import java.io.File;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScript implements CommandWithArgument {
    private String[] arg;
    Set<String> scriptsInProcess = new HashSet<>();
    CommandInvoker commandInvoker;
    Pack pack1 = new Pack();
    Request request = new Request();
    Response response = new Response();
    private Socket socket;

    ExecuteScript(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Pack execute(String nameCommand, Pack pack) {
        String fileName = arg[0];
        File file = new File(fileName);
        String absolutePath = file.getAbsolutePath();
        commandInvoker = new CommandInvoker(socket);

        if (scriptsInProcess.contains(absolutePath)) {
            IOUtils.printErr("Данный скрипт уже выполняется!");
            return null;
        }
        scriptsInProcess.add(absolutePath);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            DragonReader.setScanner(scanner);
            IOUtils.println("Попытка прочитать команды из файла " + file.getName());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                IOUtils.println("Команда " + line);
                commandInvoker.execute(line, pack1);
                request.request(pack1, socket);
                response.response(socket);
            }
            System.out.println("Все команды были успешно выполнены.");
            scanner.close();
        } catch (Exception e) {
            IOUtils.printErr("Непредвиденная ошибка при выполнении скрипта!");
        }
        scriptsInProcess.remove(absolutePath);
        DragonReader.setScanner(new Scanner(System.in));
        pack.pack(nameCommand, arg);
        return pack;
    }

    @Override
    public void arg(String[] arg) {
        this.arg = arg;
    }
}
