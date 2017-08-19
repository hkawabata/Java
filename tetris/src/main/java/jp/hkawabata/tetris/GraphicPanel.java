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

    public boolean shiftBlock(Direction d) {
        if (isShiftable(d)) {
            clearBlock();
            blk.shift(d);
            drawBlock();
            paintComponent(getGraphics());
            return true;
        } else {
            return false;
        }
    }

    public boolean isShiftable(Direction d) {
        int newPositions[][] = new int[blk.positions.length][2];
        switch (d) {
            case LEFT:
                for (int i = 0; i < blk.positions.length; i++) {
                    newPositions[i][0] = blk.positions[i][0] - 1;
                    newPositions[i][1] = blk.positions[i][1];
                }
                break;
            case RIGHT:
                for (int i = 0; i < blk.positions.length; i++) {
                    newPositions[i][0] = blk.positions[i][0] + 1;
                    newPositions[i][1] = blk.positions[i][1];
                }
                break;
            case DOWN:
                for (int i = 0; i < blk.positions.length; i++) {
                    newPositions[i][0] = blk.positions[i][0];
                    newPositions[i][1] = blk.positions[i][1] + 1;
                }
                break;
        }
        boolean ret = true;
        for (int newPos[]: newPositions) {
            ret = ret && 0 <= newPos[0] && newPos[0] < blockNumX && newPos[1] < blockNumY;
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
        int newPositions[][] = new int[blk.positions.length][2];
        for (int i = 0; i < blk.positions.length; i++) {
            newPositions[i][0] = -blk.positions[i][1] + blk.center[1] + blk.center[0];
            newPositions[i][1] = blk.positions[i][0] - blk.center[0] + blk.center[1];
        }
        boolean ret = true;
        for (int newPos[]: newPositions) {
            ret = ret && 0 <= newPos[0] && newPos[0] < blockNumX &&
                    0 <= newPos[1] && newPos[1] < blockNumY;
        }
        return ret;
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
