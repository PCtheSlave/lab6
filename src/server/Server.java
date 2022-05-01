package server;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class Server {
    private String file;

    Server(String file) {
        this.file = file;
    }

    public void run(int port) throws IOException, ClassNotFoundException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        ClientReader.reader(serverSocket);
    }
}
