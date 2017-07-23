package jp.hkawabata.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by kawabatahiroto on 2017/07/23.
 */
public class SampleFrame extends JFrame implements ActionListener {
    public static void main(String[] args) {
        SampleFrame frame = new SampleFrame("タイトル");
        frame.setVisible(true);
    }

    SampleFrame(String title) {
        setTitle(title);
        setBackground(Color.BLUE);

        // 位置（画面左上隅からの座標）とサイズを決定
        //setBounds(200, 100, 700, 300);
        // サイズを決定
        setSize(500, 500);
        // 画面中央に配置
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ボタン作成
        JButton btnWest = new JButton("West");
        btnWest.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
        JButton btnCenter = new JButton("Center");
        btnCenter.setFont(new Font("Century", Font.ITALIC, 18));
        JButton[] btnsEast = new JButton[]{
                new JButton("East_1"),
                new JButton("East_2"),
                new JButton("East_3")
        };
        JButton[] btnsSouth = new JButton[]{
                new JButton("South_1"),
                new JButton("South_2")
        };

        // ボタンにイベントリスナーを登録
        btnWest.addActionListener(this);

        // パネル作成
        SamplePanel2 pNorth = new SamplePanel2();
        SamplePanel1 pSouth = new SamplePanel1(btnsSouth, Color.BLUE);
        SamplePanel1 pEast = new SamplePanel1(btnsEast);

        // フレームにボタン・パネルを配置
        //getContentPane().add(btnWest, BorderLayout.WEST);  // 古い書き方
        add(btnWest, BorderLayout.WEST);                     // 新しい書き方
        add(btnCenter, BorderLayout.CENTER);
        add(pEast, BorderLayout.EAST);
        add(pNorth, BorderLayout.NORTH);
        add(pSouth, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        JLabel label = new JLabel("You pushed button.");
        JOptionPane.showMessageDialog(this, label);
    }
}
