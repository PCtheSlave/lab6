package server;

import server.server.Server;

public class ServerMain {
    public static void main(String[] args) {
        String file = System.getenv("FILE_PATH");
        Server server = new Server(file);
        server.run(7070);
    }
}
