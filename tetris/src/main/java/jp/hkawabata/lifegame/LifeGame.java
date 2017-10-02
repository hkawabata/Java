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
                {1, 0}, {1, 1}, {1, 2},
                {4, 2}, {4, 3}, {5, 1}, {5, 4}, {6, 2}, {6, 3},
                {9, 3}, {10, 2}, {10, 4}, {11, 3}, {11, 4},
                {14, 1}, {14, 2}, {15, 1}, {15, 2},
                {1, 8}, {1, 9}, {2, 7}, {2, 10}, {3, 7}, {3, 10}, {4, 8}, {4, 9},
                {7, 7}, {7, 8}, {8, 7}, {8, 9}, {9, 8}, {9, 9}//,
                //{, }, {, }, {, }, {, }, {, }, {, }, {, }, {, },
                /*{3, 2}, {3, 3}, {3, 4}, {3, 6}, {3, 7},*/
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
