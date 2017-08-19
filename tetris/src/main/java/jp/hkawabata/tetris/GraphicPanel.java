package jp.hkawabata.tetris;

import jp.hkawabata.tetris.block.Block1;
import jp.hkawabata.tetris.block.IBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Properties;

/**
 * Created by kawabatahiroto on 2017/08/19.
 */
public class GraphicPanel extends JPanel implements KeyListener {

    int blockSize;
    int blockNumX;
    int blockNumY;

    private Color[][] colors;
    private Color bgColor = Color.BLACK;

    public IBlock blk;

    public void setColor(int x, int y, Color color) {
        colors[x][y] = color;
    }

    public void addBlock() {
        blk = new Block1();
        drawBlock();
    }

    public void shiftBlock(IBlock.Direction d) {
        clearBlock();
        blk.shift(d);
        drawBlock();
        paintComponent(getGraphics());
    }

    public void rotateBlock() {
        clearBlock();
        blk.rotate();
        drawBlock();
        paintComponent(getGraphics());
    }

    GraphicPanel(Properties prop) {
        blockSize = Integer.parseInt(prop.getProperty("graphicframe.blocksize"));
        blockNumX = Integer.parseInt(prop.getProperty("graphicframe.blocknum.x"));
        blockNumY = Integer.parseInt(prop.getProperty("graphicframe.blocknum.y"));
        colors = new Color[blockNumX][blockNumY];
        setPreferredSize(new Dimension(blockSize * blockNumX, blockSize * blockNumY));

        for (int i = 0; i < blockNumX; i++) for (int j = 0; j < blockNumY; j++) {
            colors[i][j] = bgColor;
        }

        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i < blockNumX; i++) for (int j = 0; j < blockNumY; j++) {
            drawSquare(g, i, j);
        }
        drawLines(g);
    }

    public void drawLines(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i <= blockNumY; i++) {
            g.drawLine(0, i * blockSize, blockNumX * blockSize, i * blockSize);
        }
        for (int i = 0; i <= blockNumX; i++) {
            g.drawLine(i * blockSize, 0, i * blockSize, blockNumY * blockSize);
        }
    }

    public void drawSquare(Graphics g, int x, int y) {
        g.setColor(colors[x][y]);
        g.fillRect(blockSize * x, blockSize * y, blockSize, blockSize);
    }

    public void clearBlock() {
        for(int i = 0; i < blk.xPositions.length; i++) {
            setColor(blk.xPositions[i], blk.yPositions[i], bgColor);
        }
    }

    public void drawBlock() {
        for(int i = 0; i < blk.xPositions.length; i++) {
            setColor(blk.xPositions[i], blk.yPositions[i], blk.color);
        }
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                rotateBlock();
                break;
            case KeyEvent.VK_DOWN:
                shiftBlock(IBlock.Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                shiftBlock(IBlock.Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                shiftBlock(IBlock.Direction.RIGHT);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}
}
