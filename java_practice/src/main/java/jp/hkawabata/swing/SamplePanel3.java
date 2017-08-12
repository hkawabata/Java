package jp.hkawabata.swing;

import javafx.scene.shape.StrokeType;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by kawabatahiroto on 2017/07/23.
 */
public class SamplePanel3 extends JPanel implements ActionListener {
    JLabel textLabel;
    JTextField textField;
    JButton btn;
    JLabel imageLabel;
    JLabel imageTextLabel;
    JPasswordField passwordField;

    SamplePanel3() {
        setBackground(Color.ORANGE);

        // テキストラベル
        textLabel = new JLabel("ラベル");
        textLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 12));
        textLabel.setPreferredSize(new Dimension(80, 60));
        textLabel.setBorder(new TitledBorder("showListPanel"));
        textLabel.setHorizontalAlignment(JLabel.LEFT);
        textLabel.setVerticalAlignment(JLabel.TOP);

        // テキストフィールド
        textField = new JTextField("初期値", 15);
        textField.setToolTipText("適当な文字列を入力");
        textField.addActionListener(this);

        // テキスト取得ボタン
        btn = new JButton("テキスト取得");
        btn.addActionListener(this);
        btn.setToolTipText("全体/選択部分が読み込まれてラベルに反映されます");

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
        imageLabel = new JLabel(scaledIcon1);
        imageLabel.setPreferredSize(new Dimension(60, 60));
        imageLabel.setBorder(new LineBorder(Color.RED, 5));

        // 画像&テキストラベル
        imageTextLabel = new JLabel("Java", scaledIcon2, JLabel.CENTER);
        imageTextLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        // パスワードフィールド
        passwordField = new JPasswordField(10);
        passwordField.setEchoChar('★');

        add(textLabel);
        add(textField);
        add(btn);
        add(imageLabel);
        add(imageTextLabel);
        add(passwordField);
    }

    public void actionPerformed(ActionEvent e) {
        // 入力文字列を読み込んでラベルに反映
        textLabel.setText(textField.getText());

        // 入力文字列のうち選択部分を読み込んでラベルに反映
        // 反映後選択状態でなくなるので、再度選択状態にする
        int start = textField.getSelectionStart();
        int end = textField.getSelectionEnd();
        imageTextLabel.setText(textField.getSelectedText());
        textField.requestFocusInWindow();
        textField.select(start, end);
    }
}
