package jp.hkawabata.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by kawabatahiroto on 2017/07/23.
 */
public class SampleFrame extends JFrame implements ActionListener, MouseListener {
    JPopupMenu popup;

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
        setSize(1000, 600);
        setMinimumSize(new Dimension(600, 400));
        setMaximumSize(new Dimension(1400, 800));
        // 画面中央に配置
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ボタン作成
        JButton btnWest = new JButton("West");
        //btnWest.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
        JButton[] btnsEast = new JButton[]{
                new JButton("East_1"),
                new JButton("East_2"),
                new JButton("East_3"),
                new JButton("East_4"),
                new JButton("East_5"),
                new JButton("East_6")
        };
        btnsEast[1].setContentAreaFilled(false);
        btnsEast[2].setContentAreaFilled(false);
        btnsEast[2].setBorderPainted(false);
        btnsEast[3].setForeground(Color.RED);
        btnsEast[4].setMnemonic(KeyEvent.VK_N);  // Alt (Ctrl + Option) + N でボタンが押される
        JButton[] btnsSouth = new JButton[]{
                new JButton("South_1"),
                new JButton("South_2")
        };

        // ボタンにイベントリスナーを登録
        //btnWest.addActionListener(this);

        // パネル作成
        SamplePanel3 pNorth = new SamplePanel3();
        SamplePanel1 pSouth = new SamplePanel1(btnsSouth, Color.BLUE);
        SamplePanel2 pEast = new SamplePanel2(btnsEast);
        SamplePanel4 pCenter = new SamplePanel4();
        SamplePanel5 pWest = new SamplePanel5();

        // メニューバー作成
        JMenuBar menuBar = generateMenuBar();

        // ポップアップメニュー作成
        popup = generatePopupMenu();
        pCenter.addMouseListener(this);

        // フレームにボタン・パネルを配置
        //getContentPane().add(btnWest, BorderLayout.WEST);  // 古い書き方
        //add(btnWest, BorderLayout.WEST);                     // 新しい書き方
        add(pWest, BorderLayout.WEST);
        add(pCenter, BorderLayout.CENTER);
        add(pEast, BorderLayout.EAST);
        add(pNorth, BorderLayout.NORTH);
        add(pSouth, BorderLayout.SOUTH);
        setJMenuBar(menuBar);
    }

    public void actionPerformed(ActionEvent e) {
        /*
        JLabel label = new JLabel("You pushed button.");
        JOptionPane.showMessageDialog(this, label);
        */
        showListPanel();
    }

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}


    public JMenuBar generateMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu subMenu = new JMenu("Sub");
        JMenuItem subMenuItem1 = new JMenuItem("subItem1");
        JMenuItem subMenuItem2 = new JMenuItem("subItem2");
        JMenuItem subMenuItem3 = new JMenuItem("subItem3");
        subMenuItem3.addActionListener(this);
        subMenu.add(subMenuItem1);
        subMenu.add(subMenuItem2);
        subMenu.add(subMenuItem3);

        JMenu menu1 = new JMenu("MyApp");
        JCheckBoxMenuItem menuItem1_1 = new JCheckBoxMenuItem("item1_1");
        menuItem1_1.setSelected(true);
        JMenuItem menuItem1_2 = new JMenuItem("item1_2");
        JMenuItem menuItem1_3 = new JMenuItem("item1_3");
        menuItem1_2.setEnabled(false);
        JRadioButtonMenuItem menuItem1_4_1 = new JRadioButtonMenuItem("item1_4_1");
        JRadioButtonMenuItem menuItem1_4_2 = new JRadioButtonMenuItem("item1_4_2");
        JRadioButtonMenuItem menuItem1_4_3 = new JRadioButtonMenuItem("item1_4_3");
        ButtonGroup group = new ButtonGroup();
        group.add(menuItem1_4_1);
        group.add(menuItem1_4_2);
        group.add(menuItem1_4_3);
        menu1.add(menuItem1_1);
        menu1.add(subMenu);
        menu1.add(menuItem1_2);
        menu1.add(menuItem1_3);
        menu1.addSeparator();
        menu1.add(menuItem1_4_1);
        menu1.add(menuItem1_4_2);
        menu1.add(menuItem1_4_3);

        JMenu menu2 = new JMenu("File");
        JMenuItem menuItem2_1 = new JMenuItem("item2_1");
        JMenuItem menuItem2_2 = new JMenuItem("item2_2");
        JMenuItem menuItem2_3 = new JMenuItem("item2_3");
        menu2.add(menuItem2_1);
        menu2.add(menuItem2_2);
        menu2.add(menuItem2_3);
        menu2.setEnabled(false);
        menuBar.add(menu1);
        menuBar.add(menu2);
        return menuBar;
    }

    public void showListPanel() {
        JFrame f = new JFrame();
        f.setSize(100, 100);
        String[] strArr = {"a", "iii", "uuuuu"};
        Vector<String> strs = new Vector<String>();
        strs.addAll(Arrays.asList(strArr));
        JList<String> list = new JList<>(strs);
        JButton btn = new JButton("ok");
        f.add(list, BorderLayout.NORTH);
        f.add(btn, BorderLayout.SOUTH);
        btn.addActionListener(e -> f.setVisible(false));
        f.setVisible(true);
    }

    public JPopupMenu generatePopupMenu() {
        JPopupMenu popup = new JPopupMenu("ポップアップ");
        JMenuItem menuItem1 = new JMenuItem("操作1");
        JMenuItem menuItem2 = new JMenuItem("操作2");
        popup.add(menuItem1);
        popup.add(menuItem2);
        return popup;
    }
}
