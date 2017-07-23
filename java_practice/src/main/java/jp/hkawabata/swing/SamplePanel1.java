package jp.hkawabata.swing;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kawabatahiroto on 2017/07/23.
 */
public class SamplePanel1 extends JPanel implements ActionListener {
    SamplePanel1(JButton[] buttons){
        this(buttons, Color.PINK);
    }

    SamplePanel1(JButton[] buttons, Color color) {
        setBackground(color);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (JButton btn: buttons) {
            btn.addActionListener(this);
            add(btn);
        }
        setBorder(new BevelBorder(BevelBorder.RAISED));
    }

    public void actionPerformed(ActionEvent e) {
        JLabel label = new JLabel("You pushed button on Panel.");
        JOptionPane.showMessageDialog(this, label);
    }
}
