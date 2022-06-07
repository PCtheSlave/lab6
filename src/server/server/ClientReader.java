package server.server;

import lib.Pack;
import lib.utils.DragonFieldsReader;
import lib.utils.IOUtils;
import server.CollectionManager;
import server.commands.CommandInvoker;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class ClientReader {
    private CollectionManager collectionManager;
    private Scanner in;
    private DragonFieldsReader dragonFieldsReader = new DragonFieldsReader();
    private String str = "";

    void reader(ServerSocketChannel serverSocket, CollectionManager collectionManager) throws IOException, ClassNotFoundException {
        this.collectionManager = collectionManager;
        Selector selector;
        selector = Selector.open();
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(Integer.MAX_VALUE/2);

        while (true) {
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if (!key.isValid()) continue;
                if (key.isAcceptable()) accept(selector, serverSocket);
                if (key.isReadable()) read(key, buffer);
            }
        }
    }

    public void accept(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException {
        SocketChannel channel = serverSocketChannel.accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
    }

    public void read(SelectionKey key, ByteBuffer buffer) throws IOException, ClassNotFoundException {
        Pack pack = new Pack();
        pack.pack("");
        Request request = new Request();
        SocketChannel client = (SocketChannel) key.channel();
        CommandInvoker commandInvoker = new CommandInvoker(collectionManager, in, dragonFieldsReader, client);
        int num = client.read(buffer);
        if (num == -1) {
            client.close();
        } else {
            buffer.flip();
            pack = (Pack) request.deserialize(buffer);
            buffer.clear();
            pack = commandInvoker.execute(pack);
            if (!pack.getCommandName().equals("get_dragon")) {
                Response response = new Response();
                buffer.put(response.serialize(pack));
                buffer.flip();
                client.write(buffer);
                buffer.clear();
            }
        }
    }
}