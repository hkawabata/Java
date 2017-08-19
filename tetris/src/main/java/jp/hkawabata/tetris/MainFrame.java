package jp.hkawabata.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by kawabatahiroto on 2017/08/19.
 */
public class MainFrame extends JFrame {
    GraphicPanel gPanel;

    MainFrame(Properties prop) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Integer.parseInt(prop.getProperty("mainframe.default.width")), Integer.parseInt(prop.getProperty("mainframe.default.height")));
        setMinimumSize(new Dimension(Integer.parseInt(prop.getProperty("mainframe.min.width")), Integer.parseInt(prop.getProperty("mainframe.min.height"))));
        setMaximumSize(new Dimension(Integer.parseInt(prop.getProperty("mainframe.max.width")), Integer.parseInt(prop.getProperty("mainframe.max.height"))));
        setLocationRelativeTo(null);

        gPanel = new GraphicPanel(prop);
        JButton btn = new JButton("hoge");
        JLabel label = new JLabel("fuga");
        add(gPanel, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        add(label, BorderLayout.NORTH);
        addKeyListener(gPanel);
        btn.addKeyListener(gPanel);
    }

}
