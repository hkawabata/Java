package jp.hkawabata.swing;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by kawabatahiroto on 2017/07/23.
 */
public class SamplePanel2 extends JPanel {
    SamplePanel2() {
        setBackground(Color.ORANGE);

        // テキストラベル
        JLabel label = new JLabel("ラベル");
        label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 12));

        // テキストフィールド
        JTextField textField = new JTextField(5);

        // 画像ラベル
        URL imagePath = getClass().getClassLoader().getResource("java.gif");
        System.out.println(imagePath);
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(40, -1, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);

        add(label);
        add(textField);
        add(imageLabel);
    }
}
