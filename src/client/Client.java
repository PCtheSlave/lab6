package client;

import lib.collection.DragonFactory;
import lib.utils.DragonFieldsReader;
import lib.utils.IO;
import server.CollectionManager;
import server.commands.CommandInvoker;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    CollectionManager collectionManager = new CollectionManager();
    Scanner in;
    DragonFieldsReader dragonFieldsReader = new DragonFieldsReader();
    DragonFactory dragonFactory = new DragonFactory();
    CommandInvoker commandInvoker = new CommandInvoker(collectionManager, in, dragonFieldsReader);
    void run() throws UnknownHostException {
        IO.Print("Введите порт: ");
        int port = Integer.parseInt(IO.Input());
        InetAddress address = Inet4Address.getByName("localhost");

        try {
            Socket socket = new Socket(address, port);
            while (true) {
                String str = IO.Input();
                Object obj = null;
                if (str.equals("add")) {
                    obj = dragonFactory.createDragon();
                } else {
                    obj = str;
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(obj);
                socket.getOutputStream().write(byteArrayOutputStream.toByteArray());
                socket.getOutputStream().flush();
                byte[] bytes = new byte[32768];
                socket.getInputStream().read(bytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
                System.out.println("hi " + objectInputStream.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
