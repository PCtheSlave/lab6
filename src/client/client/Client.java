package client.client;

import client.command.CommandInvoker;
import lib.Pack;
import lib.utils.IOUtils;

import java.io.*;
import java.net.*;

public class Client {
    private Pack pack = new Pack();
    private Response response = new Response();
    private Request request = new Request();

    public void run(int port) {
        InetAddress address = null;
        try {
            address = Inet4Address.getByName("localhost");
            while (true) {
                Socket socket = new Socket(address, port);
                CommandInvoker commandInvoker = new CommandInvoker(socket);
                IOUtils.print("Введите комманду: ");
                String str = IOUtils.input();
                if (commandInvoker.execute(str, pack) & !str.startsWith("update")) {
                    request.request(pack, socket);
                    if (str.trim().equalsIgnoreCase("show"))
                        response.responseShow(socket);
                    else
                        response.response(socket);
                    if (str.trim().equalsIgnoreCase("exit"))
                        System.exit(0);
                }
            }
        } catch (IOException e) {
            IOUtils.printErr("Повторная попытка подключения через 5 секунд!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            run(port);
        } catch (ClassNotFoundException e) {
            IOUtils.printErr("Сериализуемый класс не наследует класс Serializable");
        }
    }
}
