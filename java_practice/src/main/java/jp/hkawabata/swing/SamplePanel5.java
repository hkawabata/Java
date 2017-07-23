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

    SamplePanel5() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 500));
        JButton btn = new JButton("更新");
        btn.addActionListener(this);

        add(btn, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        drawBlock(getGraphics(), Color.RED, 1, 3);
        drawBlock(getGraphics(), Color.RED, 1, 5);
        drawBlock(getGraphics(), Color.RED, 3, 7);
        drawBlock(getGraphics(), Color.RED, 3, 8);
        drawLine(getGraphics());
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 10 * blockSize, 10 * blockSize);
        drawLine(g);
    }

    public void drawLine(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i <= 10; i++) {
            g.drawLine(0, i * blockSize, 10 * blockSize, i * blockSize);
        }
        for (int i = 0; i <= 10; i++) {
            g.drawLine(i * blockSize, 0, i * blockSize, 10 * blockSize);
        }
    }

    public void drawBlock(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(10 * x, 10 * y, 10, 10);
    }
}
