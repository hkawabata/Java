package jp.hkawabata.network.tcpip;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;

    private Server(int port) {
        this.port = port;
    }

    private void run() {
        try {
            ServerSocket ss = new ServerSocket(port);

            System.out.println("Waiting...");
            while(true) {
                // クライアントからのリクエスト待ち
                Socket s = ss.accept();

                // ここより下はクライアントからのリクエストを受けてから動く
                InputStream is = s.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                int req = dis.readInt();
                System.out.println("recieved request: " + req);

                OutputStream os = s.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(req * req);

                dos.close();
                dis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(Integer.parseInt(args[0]));
        server.run();
    }
}
