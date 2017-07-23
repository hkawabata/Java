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

        add(scrollPane);
        add(btn);
        add(outputScrollPane);
    }

    public void actionPerformed(ActionEvent e) {
        String newText = process(textArea.getText());
        outputTextArea.setText(newText);
    }

    private static String process(String input) {
        java.util.List<String> lines = Arrays.asList(input.split("\n"));
        Collections.reverse(lines);
        return String.join("\n", lines);
    }
}
