package jp.hkawabata.network.tcpip;

public class Main {
    public static void main(String[] args) {
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        System.out.println(hostname + ":" + port);
        Client client = new Client(hostname, port);
        System.out.println(client.request(30));
        System.out.println(client.request(12));
    }
}
