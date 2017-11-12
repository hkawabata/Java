package jp.hkawabata.network.tcpip;

import java.io.*;
import java.net.Socket;

public class Client {
    private String hostname;
    private int port;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public int request(int n) {
        try {
            Socket s = new Socket(hostname, port);

            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeInt(n);

            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            int res = dis.readInt();

            dis.close();
            dos.close();

            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
