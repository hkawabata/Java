package jp.hkawabata.tetris.block;

import java.awt.*;
import java.util.Properties;

/**
 * Created by kawabatahiroto on 2017/08/19.
 */
public abstract class IBlock {
    public int xPositions[];
    public int yPositions[];
    public int xCenter;
    public int yCenter;
    private int xPositionsTmp[];
    public Color color;

    public void shift(Direction d) {
        // TODO: そっちには動けません判定
        // TODO: 下に shift しようとしてもうこれ以上動けない場合、ステータスを返すか何かして次のブロック投入のトリガーにする
        if (isShiftable(d)) {
            switch (d) {
                case RIGHT:
                    for (int i = 0; i < xPositions.length; i++) {
                        xPositions[i] += 1;
                    }
                    xCenter += 1;
                    break;
                case LEFT:
                    for (int i = 0; i < xPositions.length; i++) {
                        xPositions[i] -= 1;
                    }
                    xCenter -= 1;
                    break;
                case DOWN:
                    for (int i = 0; i < yPositions.length; i++) {
                        yPositions[i] += 1;
                    }
                    yCenter += 1;
                    break;
            }
        }
    }

    public boolean isShiftable(Direction d) {
        return true;
    }

    public void rotate() {
        // TODO: 回転できません判定
        if (isRotatable()) {
            for (int i = 0; i < xPositions.length; i++) {
                int xTmp = xPositions[i];
                int yTmp = yPositions[i];
                xPositions[i] = -yTmp + yCenter + xCenter;
                yPositions[i] = xTmp - xCenter + yCenter;
            }
        }
    }

    public boolean isRotatable() {
        return true;
    }

    public enum Direction {LEFT, RIGHT, DOWN}
}
