package server.server;

import lib.Pack;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class Response {
    public ByteBuffer serialize(Pack pack) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(pack);
        return ByteBuffer.wrap(baos.toByteArray());
    }
}
