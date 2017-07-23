package jp.hkawabata.swing;

import javafx.scene.shape.StrokeType;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.StrokeBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.net.URL;

/**
 * Created by kawabatahiroto on 2017/07/23.
 */
public class SamplePanel3 extends JPanel {
    SamplePanel3() {
        setBackground(Color.ORANGE);

        // テキストラベル
        JLabel textLabel = new JLabel("ラベル");
        textLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 12));
        textLabel.setPreferredSize(new Dimension(80, 60));
        textLabel.setBorder(new TitledBorder("hoge"));
        textLabel.setHorizontalAlignment(JLabel.LEFT);
        textLabel.setVerticalAlignment(JLabel.TOP);

        // テキストフィールド
        JTextField textField = new JTextField(5);

        // 画像
        URL imagePath1 = getClass().getClassLoader().getResource("image/duke.gif");
        ImageIcon icon1 = new ImageIcon(imagePath1);
        Image scaledImage1 = icon1.getImage().getScaledInstance(40, -1, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        URL imagePath2 = getClass().getClassLoader().getResource("image/java.png");
        ImageIcon icon2 = new ImageIcon(imagePath2);
        Image scaledImage2 = icon2.getImage().getScaledInstance(40, -1, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);

        // 画像ラベル
        JLabel imageLabel = new JLabel(scaledIcon1);
        imageLabel.setPreferredSize(new Dimension(60, 60));
        imageLabel.setBorder(new LineBorder(Color.RED, 5));

        // 画像&テキストラベル
        JLabel imageTextLabel = new JLabel("Java", scaledIcon2, JLabel.CENTER);
        imageTextLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        add(textLabel);
        add(textField);
        add(imageLabel);
        add(imageTextLabel);
    }
}
