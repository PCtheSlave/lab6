package server;

import lib.collection.Dragon;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ClientReader {

    static void reader(ServerSocketChannel serverSocket) throws IOException, ClassNotFoundException {
        Selector selector = Selector.open();
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(32768);

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

    public static void accept(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException {
        SocketChannel channel = serverSocketChannel.accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
    }

    public static void read(SelectionKey key, ByteBuffer buffer) throws IOException, ClassNotFoundException {
        RequestHandler requestHandler = new RequestHandler();
        ServerUtil serverUtil = new ServerUtil();
        SocketChannel client = (SocketChannel) key.channel();
        CollectionManager collectionManager = new CollectionManager();
        int num = client.read(buffer);
        if (num == -1) {
            client.close();
            System.out.println("Client disable");
        } else {
            buffer.flip();
            Object obj = requestHandler.deserialize(buffer);
            collectionManager.insert((Dragon) obj);
            System.out.println(collectionManager.getDragons());
            client.write(serverUtil.serialize("Дракон получен"));
            buffer.clear();
        }
    }
}