package server;

import lib.utils.IOUtils;

import java.sql.*;

/**
 * Created by Meldren on 16/03/2022
 */

public class PostgreSQLDatabase  {

    public PostgreSQLDatabase(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    private static final String URL = "jdbc:postgresql://%s:%d/%s" +
            "?autoReconnect=true" +
            "&useSSL=false" +
            "&useUnicode=true" +
            "&characterEncoding=utf-8";

    String host;
    int port;
    String database, username, password;
    Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(String.format(URL, host, port, database),
                    username, password);
            IOUtils.println("Соединение с базой данных успешно установлено.");
        } catch (SQLException ex) {
            IOUtils.println("Соединение с базой данных не было установлено: отключение.");
            ex.printStackTrace();
            System.exit(0);
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        ResultSet result;
        Statement statement = this.connection.createStatement();
        if (query.contains("SELECT")) {
            result = statement.executeQuery(query);
        } else {
            statement.executeUpdate(query);
            result = statement.getGeneratedKeys();
        }
        return result;
    }

    public void disconnect() {
        if (connection == null) return;
        try {
            connection.close();
            IOUtils.print("Соединение с базой данных закрыто.");
        } catch (SQLException ignored) {}
    }
}
