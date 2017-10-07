package jp.hkawabata.lifegame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Properties;

public class GraphicPanel extends JPanel {
    int blockSize;
    int blockNumX;
    int blockNumY;

    private Color[][] colors;
    private Color[][] colorsBuffer;
    private Color bgColor = Color.BLACK;
    private Color aliveColor = Color.RED;

    GraphicPanel(Properties prop, int[][] aliveCells) {
        blockSize = Integer.parseInt(prop.getProperty("graphicframe.blocksize"));
        blockNumX = Integer.parseInt(prop.getProperty("graphicframe.blocknum.x"));
        blockNumY = Integer.parseInt(prop.getProperty("graphicframe.blocknum.y"));
        colors = new Color[blockNumX][blockNumY];
        colorsBuffer = new Color[blockNumX][blockNumY];
        setPreferredSize(new Dimension(blockSize * blockNumX, blockSize * blockNumY));

        for (int i = 0; i < blockNumX; i++) for (int j = 0; j < blockNumY; j++) {
            colors[i][j] = bgColor;
        }
        for (int[] xy: aliveCells) {
            colors[xy[0]][xy[1]] = aliveColor;
        }
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i < blockNumX; i++) for (int j = 0; j < blockNumY; j++) {
            drawSquare(g, i, j);
        }
        //drawLines(g);
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


    public void check() {
        for (int y = 0; y < blockNumY; y++) for (int x = 0; x < blockNumX; x++) {
            if (colors[x][y].equals(aliveColor)) {
                if (numOfAliveAround(x, y) >= 4 || numOfAliveAround(x, y) <= 1) {
                    // 過密 or 過疎で死亡
                    colorsBuffer[x][y] = bgColor;
                } else {
                    // 生存
                    colorsBuffer[x][y] = aliveColor;
                }
            } else {
                if (numOfAliveAround(x, y) == 3) {
                    // 生命誕生
                    colorsBuffer[x][y] = aliveColor;
                } else {
                    // 何も起こらない
                    colorsBuffer[x][y] = bgColor;
                }
            }
        }

        for (int y = 0; y < blockNumY; y++) for (int x = 0; x < blockNumX; x++) {
            colors[x][y] = colorsBuffer[x][y];
        }

        paintComponent(getGraphics());
    }

    private int numOfAliveAround(int x, int y) {
        java.util.List<Integer> targetX = new ArrayList<Integer>();
        java.util.List<Integer> targetY = new ArrayList<Integer>();

        targetX.add(x);
        if (x == 0) {
            targetX.add(x + 1);
        } else if (x == blockNumX - 1) {
            targetX.add(x - 1);
        } else {
            targetX.add(x + 1);
            targetX.add(x - 1);
        }
        targetY.add(y);
        if (y == 0) {
            targetY.add(y + 1);
        } else if (y == blockNumY - 1) {
            targetY.add(y - 1);
        } else {
            targetY.add(y + 1);
            targetY.add(y - 1);
        }

        int cnt = 0;
        for (Integer ix: targetX) for (Integer iy: targetY){
            if (ix.equals(x) && iy.equals(y)) {
                continue;
            } else if (colors[ix][iy].equals(aliveColor)) {
                cnt ++;
            }
        }
        return cnt;
    }
}
