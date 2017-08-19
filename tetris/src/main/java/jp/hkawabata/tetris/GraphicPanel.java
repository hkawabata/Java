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
        return isValidNewPosition(newPositions);
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
        return isValidNewPosition(newPositions);
    }

    private boolean isValidNewPosition(int newPositions[][]) {
        boolean ret = true;
        for (int newPos[]: newPositions) {
            // 壁を貫通しないかチェック
            ret = ret && 0 <= newPos[0] && newPos[0] < blockNumX && newPos[1] < blockNumY;
            if (ret && !colors[newPos[0]][newPos[1]].equals(bgColor)) {
                // 壁貫通はないが、移動先にすでにブロックがあるとき
                // 移動前の自分自身と重なる場合のみ OK
                boolean isOverlap = false;
                for (int pos[] : blk.positions) {
                    isOverlap = isOverlap || (newPos[0] == pos[0] && newPos[1] == pos[1]);
                }
                ret = isOverlap;
            }
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

    public int check() {
        int cntFilledLines = 0;
        for (int y = 0; y < blockNumY; y++) {
            boolean isFilledLine = true;
            for (int x = 0; x < blockNumX; x++) {
                isFilledLine = isFilledLine && !colors[x][y].equals(bgColor);
            }
            if (isFilledLine) {
                cntFilledLines++;
                for (int x = 0; x < blockNumX; x++) {
                    colors[x][y] = bgColor;
                }
                for (int y_ = y; y_ > 0; y_--) for (int x = 0; x < blockNumX; x++) {
                    colors[x][y_] = colors[x][y_ - 1];
                }
                for (int x = 0; x < blockNumX; x++) {
                    colors[x][0] = bgColor;
                }
            }
        }
        paintComponent(getGraphics());
        return cntFilledLines * (cntFilledLines + 1) * 1000 / 2;
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
