package jp.hkawabata.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


public class ZooKeeperWatcher {
    private String zkData;
    // カンマ区切りの ZK サーバ一覧
    private String zkHosts;
    private ZooKeeper zk;
    private String znode;
    // ZooKeeper サーバとの接続が確立されるまで待つためのオブジェクト
    private CountDownLatch connectionFlag = new CountDownLatch(1);
    private boolean isConnected() {
        return connectionFlag.getCount() == 0;
    }

    private ZooKeeperWatcher(String zkHosts, String znode) throws Exception {
        this.zkHosts = zkHosts;
        this.znode = znode;
        connect();
        fetchData();
    }

    private void connect() throws IOException, InterruptedException {
        connectionFlag = new CountDownLatch(1);
        zk = new ZooKeeper(zkHosts, 5000,
                new Watcher() {
                    public void process(WatchedEvent event) {
                        System.out.println("Received ZK event: " + event.toString());
                        // 未接続状態のとき SyncConnected イベントを受け取ったら接続フラグを立てる
                        if(!isConnected() && event.getState() == Event.KeeperState.SyncConnected) {
                            connectionFlag.countDown();
                        }
                        // ウォッチしている znode の値が変更されたら
                        // 再フェッチして変数の値を更新
                        if(event.getType() == Event.EventType.NodeDataChanged) {
                            try {
                                zkData = new String(zk.getData(znode, true, null));
                            } catch (KeeperException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else if (event.getState() == Event.KeeperState.Expired) {
                            System.out.println("Session Expired. Reconnecting to ZK...");
                            try {
                                connect();
                                System.out.println("Reconnected to ZK.");
                            } catch (IOException | InterruptedException e) {
                                System.out.println("Error to reconnect ZK.");
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        // ZooKeeper インスタンスが生成されても接続は未確立の場合があるため接続確立を待つ
        connectionFlag.await();
    }

    private void fetchData() throws Exception {
        zkData = new String(zk.getData(znode, true, null));
    }

    public static void main(String[] args) throws Exception {
        String zkHosts = "node001.hkawabata.jp";
        String znode = "/zk_test";
        ZooKeeperWatcher zkw = new ZooKeeperWatcher(zkHosts, znode);
        zkw.fetchData();

        // 1秒おきに30秒間、zkData の内容をプリント
        for(int i = 0; i < 30; i++) {
            System.out.println(zkw.zkData);
            Thread.sleep(1000);
        }
    }
}
