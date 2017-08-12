package jp.hkawabata.swing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by kawabatahiroto on 2017/07/23.
 */
public class SamplePanel4 extends JPanel implements ActionListener, ChangeListener {
    JTextArea textArea;
    JScrollPane scrollPane;
    JButton btnTextField;
    JTextArea outputTextArea;
    JScrollPane outputScrollPane;
    JCheckBox checkBox;
    JRadioButton radioBtn1;
    JRadioButton radioBtn2;
    JRadioButton radioBtn3;
    JPanel radioPanel;
    ButtonGroup radioBtns;
    JSlider slider;
    JButton btnSlider;
    JLabel labelSlider1;
    JLabel labelSlider2;

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
        btnTextField = new JButton("テキスト取得");
        btnTextField.addActionListener(this);

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

        // スライダー
        slider = new JSlider(JSlider.VERTICAL, 0, 100, 20);
        slider.addChangeListener(this);
        slider.setInverted(true);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(2);
        slider.setPaintTicks(true);  // 目盛りがある場所にしかノブが止まらなくなる
        slider.setSnapToTicks(true);
        //slider.setLabelTable(slider.createStandardLabels(20));
        Hashtable<Integer, JComponent> labelTable;
        labelTable = new Hashtable<>();
        labelTable.put(20, new JLabel("初期値"));
        labelTable.put(0, new JLabel("最小値"));
        labelTable.put(100, new JLabel("最大値"));
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        labelSlider1 = new JLabel(Integer.toString(slider.getValue()));
        labelSlider2 = new JLabel(Integer.toString(slider.getValue()));
        btnSlider = new JButton("値を取得");
        btnSlider.addActionListener(this);

        add(scrollPane);
        add(btnTextField);
        add(outputScrollPane);
        add(checkBox);
        add(radioPanel);
        add(slider);
        add(labelSlider1);
        add(labelSlider2);
        add(btnSlider);
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == btnTextField) {
            String newText = processInputText(textArea.getText());
            outputTextArea.setText(newText);
        } else if (obj == btnSlider) {
            System.out.println(slider.getValue());
        }
    }

    private static String processInputText(String input) {
        java.util.List<String> lines = Arrays.asList(input.split("\n"));
        Collections.reverse(lines);
        return String.join("\n", lines);
    }

    public void stateChanged(ChangeEvent e) {
        Object obj = e.getSource();
        if (obj == slider) {
            labelSlider1.setText(Integer.toString(slider.getValue()));
            if (!slider.getValueIsAdjusting()) {
                // スライダーを動かし終わったときに初めてこの処理が行われる
                labelSlider2.setText(Integer.toString(slider.getValue()));
            }
        }
    }
}
