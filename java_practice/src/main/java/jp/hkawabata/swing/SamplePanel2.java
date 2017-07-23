package jp.hkawabata.swing;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kawabatahiroto on 2017/07/23.
 */
public class SamplePanel2 extends JPanel implements ActionListener {
    private static Color[] colors = new Color[]{
            Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.ORANGE, Color.YELLOW
    };

    private int colorNum = 0;

    SamplePanel2(JButton[] buttons) {
        setBackground(colors[0]);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (JButton btn: buttons) {
            btn.addActionListener(this);
            add(btn);
        }
        setBorder(new BevelBorder(BevelBorder.RAISED));
    }

    public void actionPerformed(ActionEvent e) {
        JLabel label = new JLabel("You pushed button on Panel.");
        colorNum = (colorNum + 1) % colors.length;
        setBackground(colors[colorNum]);
    }
}
