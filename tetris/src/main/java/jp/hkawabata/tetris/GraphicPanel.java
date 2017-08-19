package jp.hkawabata.tetris;

import jp.hkawabata.tetris.Block.Direction;

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

    public Block blk;

    public void setColor(int x, int y, Color color) {
        colors[x][y] = color;
    }

    public void addBlock() {
        blk = new Block();
        drawBlock();
    }

    public void shiftBlock(Direction d) {
        if (isShiftable(d)) {
            clearBlock();
            blk.shift(d);
            drawBlock();
            paintComponent(getGraphics());
        }
    }

    public boolean isShiftable(Direction d) {
        boolean ret = true;
        switch (d) {
            case LEFT:
                for (int pos[]: blk.positions) {
                    ret = ret && 0 <= pos[0] - 1 &&
                            pos[0] - 1 < blockNumX;
                }
                break;
            case RIGHT:
                for (int pos[]: blk.positions) {
                    ret = ret && 0 <= pos[0] + 1 &&
                            pos[0] + 1 < blockNumX;
                }
                break;
            case DOWN:
                for (int pos[]: blk.positions) {
                    ret = ret && pos[1] + 1 < blockNumY;
                }
                break;
        }
        return ret;
    }

    public void rotateBlock() {
        if (isRotatable()) {
            clearBlock();
            blk.rotate();
            drawBlock();
            paintComponent(getGraphics());
        }
    }

    public boolean isRotatable() {
        return true;
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
        for(int i = 0; i < blk.positions.length; i++) {
            setColor(blk.positions[i][0], blk.positions[i][1], bgColor);
        }
    }

    public void drawBlock() {
        for(int i = 0; i < blk.positions.length; i++) {
            setColor(blk.positions[i][0], blk.positions[i][1], blk.color);
        }
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                rotateBlock();
                break;
            case KeyEvent.VK_DOWN:
                shiftBlock(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                shiftBlock(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                shiftBlock(Direction.RIGHT);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}
}
