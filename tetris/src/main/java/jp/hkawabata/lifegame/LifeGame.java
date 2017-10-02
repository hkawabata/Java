package jp.hkawabata.lifegame;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LifeGame {
    static MainFrame mainFrame;

    public static void main(String[] args) {
        // 設定ファイル読み込み
        InputStream is = LifeGame.class.getClassLoader().getResourceAsStream("lifegame.properties");
        Properties prop = new Properties();
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int sleepTime = Integer.parseInt(prop.getProperty("lifegame.default.sleeptime.ms"));

        // 最初の生きたセル
        int[][] aliveCells = {
                {1, 2}, {1, 3}, {1, 4}, {1, 6}, {1, 7},
                /*{3, 2}, {3, 3}, {3, 4}, {3, 6}, {3, 7},*/
                {5, 2}, {5, 3}, {5, 4}, {5, 6}, {5, 7}
        };

        mainFrame = new MainFrame(prop, aliveCells);
        mainFrame.setVisible(true);

        while(mainFrame.isVisible()) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainFrame.gPanel.check();
        }
    }
}
