package jp.hkawabata.lifegame;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class MainFrame extends JFrame {
    GraphicPanel gPanel;
    JLabel label;

    MainFrame(Properties prop, int[][] aliveCells) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Integer.parseInt(prop.getProperty("mainframe.default.width")), Integer.parseInt(prop.getProperty("mainframe.default.height")));
        setLocationRelativeTo(null);

        gPanel = new GraphicPanel(prop, aliveCells);
        add(gPanel, BorderLayout.CENTER);
    }
}
