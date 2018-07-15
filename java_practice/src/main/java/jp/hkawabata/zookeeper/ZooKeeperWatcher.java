package jp.hkawabata.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;


public class ZooKeeperWatcher {
    private String zkData;
    private ZooKeeper zk;
    private String znode = "/zk_test";

    private ZooKeeperWatcher(String zkHost) throws Exception {
        this.zk = new ZooKeeper(zkHost, 5000,
                new Watcher() {
                    public void process(WatchedEvent event) {
                        System.out.println(event.toString());
                        // ウォッチしている znode の値が変更されたら
                        // 再フェッチして変数の値を更新
                        if(event.getType() == Event.EventType.NodeDataChanged) {
                            try {
                                zkData = new String(zk.getData(znode, true, null));
                            } catch (KeeperException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }

    private void fetchData() throws Exception {
        zkData = new String(zk.getData(znode, true, null));
    }

    public static void main(String[] args) throws Exception {
        String zkHost = "node001.hkawabata.jp";
        ZooKeeperWatcher zkw = new ZooKeeperWatcher(zkHost);
        zkw.fetchData();

        // 1秒おきに30秒間、zkData の内容をプリント
        for(int i = 0; i < 30; i++) {
            System.out.println(zkw.zkData);
            Thread.sleep(1000);
        }
    }
}
