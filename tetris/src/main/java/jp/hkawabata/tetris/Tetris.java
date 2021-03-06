package jp.hkawabata.tetris;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

/**
 * Tetris with swing
 *
 * Created by kawabatahiroto on 2017/08/19.
 */
public class Tetris {
    static MainFrame mainFrame;
    static Random random = new Random();
    static int score = 0;

    public static void main(String[] args) {
        // 設定ファイル読み込み
        InputStream is = Tetris.class.getClassLoader().getResourceAsStream("tetris.properties");
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

        int sleepTime = Integer.parseInt(prop.getProperty("tetris.default.sleeptime.ms"));

        // メインフレーム作成
        mainFrame = new MainFrame(prop);
        mainFrame.setVisible(true);

        // ブロックを投下
        mainFrame.gPanel.addBlock();

        while(mainFrame.isVisible()) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean downResult = mainFrame.gPanel.shiftBlock(Block.Direction.DOWN);
            if (!downResult) {
                score += mainFrame.gPanel.check();
                mainFrame.label.setText(Integer.toString(score));
                mainFrame.gPanel.addBlock();
            }
        }
    }
}
