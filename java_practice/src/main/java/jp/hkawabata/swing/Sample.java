package jp.hkawabata.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by kawabatahiroto on 2017/07/23.
 */
public class Sample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("タイトル");
        frame.setBounds(200, 100, 700, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ボタン作成
        JButton btn1 = new JButton("West");
        JButton btn2 = new JButton("Center");
        JButton btn3 = new JButton("East");
        JButton btn4 = new JButton("North");
        JButton btn5_1 = new JButton("South_1");
        JButton btn5_2 = new JButton("South_2");
        JButton btn5_3 = new JButton("South_3");

        // パネル作成
        JPanel p = new JPanel();
        p.add(btn5_1);
        p.add(btn5_2);
        p.add(btn5_3);

        // フレームにボタン・パネルを配置
        frame.getContentPane().add(btn1, BorderLayout.WEST);
        frame.getContentPane().add(btn2, BorderLayout.CENTER);
        frame.getContentPane().add(btn3, BorderLayout.EAST);
        frame.getContentPane().add(btn4, BorderLayout.NORTH);
        frame.getContentPane().add(p, BorderLayout.SOUTH);

        // フレーム表示
        frame.setVisible(true);
    }
}
