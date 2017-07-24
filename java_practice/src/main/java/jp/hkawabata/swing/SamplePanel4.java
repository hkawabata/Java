package jp.hkawabata.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by kawabatahiroto on 2017/07/23.
 */
public class SamplePanel4 extends JPanel implements ActionListener {
    JTextArea textArea;
    JScrollPane scrollPane;
    JButton btn;
    JTextArea outputTextArea;
    JScrollPane outputScrollPane;
    JCheckBox checkBox;
    JRadioButton radioBtn1;
    JRadioButton radioBtn2;
    JRadioButton radioBtn3;
    JPanel radioPanel;
    ButtonGroup radioBtns;

    SamplePanel4() {
        setBackground(Color.PINK);
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(JPanel.LEFT_ALIGNMENT);

        // テキストエリア & スクロールペイン
        textArea = new JTextArea();
        textArea.setLineWrap(true);  // 行が長いとき、テキストエリアを横に伸ばすのではなく、折り返しを行う
        textArea.setWrapStyleWord(true);  // 折り返しのとき単語を分割しない
        textArea.setTabSize(4);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        // テキスト取得ボタン
        btn = new JButton("テキスト取得");
        btn.addActionListener(this);

        // 取得したテキストを書き出すラベル
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setBackground(Color.LIGHT_GRAY);
        outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setPreferredSize(new Dimension(300, 200));

        // チェックボックス
        checkBox = new JCheckBox("send me e-mail");
        checkBox.setSelected(true);

        // ラジオボタン
        radioBtn1 = new JRadioButton("apple");
        radioBtn2 = new JRadioButton("orange");
        radioBtn3 = new JRadioButton("grape");
        radioPanel = new JPanel();
        radioPanel.add(radioBtn1);
        radioPanel.add(radioBtn2);
        radioPanel.add(radioBtn3);
        radioBtns = new ButtonGroup();  // グループ内のラジオボタンは1つしか選択できない
        radioBtns.add(radioBtn1);
        radioBtns.add(radioBtn2);
        radioBtns.add(radioBtn3);

        add(scrollPane);
        add(btn);
        add(outputScrollPane);
        add(checkBox);
        add(radioPanel);
    }

    public void actionPerformed(ActionEvent e) {
        String newText = processInputText(textArea.getText());
        outputTextArea.setText(newText);
    }

    private static String processInputText(String input) {
        java.util.List<String> lines = Arrays.asList(input.split("\n"));
        Collections.reverse(lines);
        return String.join("\n", lines);
    }
}
