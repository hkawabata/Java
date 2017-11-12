package jp.hkawabata.network.tcpip;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

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

    private void runHttpServer() {
        try {
            ServerSocket ss = new ServerSocket(port);

            System.out.println("Waiting...");

            BufferedReader br = null;
            PrintWriter pw = null;
            while(true) {
                // クライアントからのリクエスト待ち
                Socket s = ss.accept();

                // ここより下はクライアントからのリクエストを受けてから動く
                try {
                    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    pw = new PrintWriter(s.getOutputStream());

                    pw.print("HTTP/1.1 200 OK\n");
                    pw.print("\n");
                    pw.print("hoge\n");
                    pw.flush();

                    StringBuilder header = new StringBuilder();
                    String body = null;

                    // ヘッダ解析
                    String line;
                    int contentLength = 0;
                    while ((line = br.readLine()) != null && !line.isEmpty()) {
                        if (line.startsWith("Content-Length:")) {
                            contentLength = Integer.parseInt(line.split(":")[1].trim());
                        }
                        header.append(line).append("\n");
                        //System.out.println(line);
                    }

                    // ボディ解析
                    if (0 < contentLength) {
                        char c[] = new char[contentLength];
                        br.read(c);
                        body = new String(c);
                    }

                    System.out.println(header);
                    System.out.println(body);
                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                        if (pw != null) {
                            pw.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(Integer.parseInt(args[0]));
        //server.run();
        server.runHttpServer();
    }
}
