package jp.hkawabata.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kawabatahiroto on 2017/07/23.
 */
public class SamplePanel5 extends JPanel implements ActionListener {

    static int blockSize = 10;
    static int blockNumX = 8;
    static int blockNumY = 10;

    Color[][] colors = new Color[blockNumX][blockNumY];

    SamplePanel5() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 500));
        JButton btn = new JButton("更新");
        btn.addActionListener(this);
        add(btn, BorderLayout.SOUTH);

        for (int i = 0; i < blockNumX; i++) for (int j = 0; j < blockNumY; j++) {
            colors[i][j] = Color.BLACK;
        }
    }

    public void actionPerformed(ActionEvent e) {
        colors[1][3] = Color.RED;
        colors[1][5] = Color.RED;
        colors[3][7] = Color.RED;
        colors[3][8] = Color.RED;
        paintComponent(getGraphics());
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i < blockNumX; i++) for (int j = 0; j < blockNumY; j++) {
            drawBlock(g, colors[i][j], i, j);
        }
        drawLine(g);
    }

    public void drawLine(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i <= blockNumY; i++) {
            g.drawLine(0, i * blockSize, blockNumX * blockSize, i * blockSize);
        }
        for (int i = 0; i <= blockNumX; i++) {
            g.drawLine(i * blockSize, 0, i * blockSize, blockNumY * blockSize);
        }
    }

    public void drawBlock(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(blockSize * x, blockSize * y, blockSize, blockSize);
    }
}
