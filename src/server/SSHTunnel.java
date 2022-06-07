package server;

import com.jcraft.jsch.*;
import lib.utils.IOUtils;

/**
 * Created by Meldren on 16/03/2022
 */

public class SSHTunnel {

    String host, user, password;
    int port;
    String tunnelRemoteHost;
    int tunnelLocalPort, tunnelRemotePort;

    public SSHTunnel(String host, String user, String password, int port, String tunnelRemoteHost, int tunnelLocalPort, int tunnelRemotePort) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.port = port;
        this.tunnelRemoteHost = tunnelRemoteHost;
        this.tunnelLocalPort = tunnelLocalPort;
        this.tunnelRemotePort = tunnelRemotePort;
    }

    public int create() {
        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setUserInfo(new LocalUserInfo());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(3000);
            return session.setPortForwardingL(tunnelLocalPort, tunnelRemoteHost, tunnelRemotePort);
        } catch (JSchException ex) {
            IOUtils.println("Подключение через SSH-туннель не было установлено: отключение.");
            System.exit(0);
        }
        return -1;
    }

    private class LocalUserInfo implements UserInfo {

        @Override
        public String getPassphrase() {
            return null;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public boolean promptPassword(String s) {
            return true;
        }

        @Override
        public boolean promptPassphrase(String s) {
            return true;
        }

        @Override
        public boolean promptYesNo(String s) {
            return true;
        }

        @Override
        public void showMessage(String s) {}
    }
}
