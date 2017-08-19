package jp.hkawabata.tetris;

import java.awt.*;
import java.util.Properties;
import java.util.Random;

/**
 * Created by kawabatahiroto on 2017/08/19.
 */
public class Block {
    /*
    public int xPositions[];
    public int yPositions[];
    public int xCenter;
    public int yCenter;
    */
    public int positions[][];
    public int center[];
    public Color color;

    public static final int positionsCandidates[][][] = {
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
            {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {0, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {1, 1}}
    };
    public static final int centerCandidates[][] = {
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0}
    };
    public static final Color colorCandidates[] = {
            Color.CYAN,
            Color.YELLOW,
            Color.ORANGE,
            Color.GREEN,
            Color.MAGENTA
    };
    public static final Random random = new Random();

    public Block() {
        int blkId = Math.abs(random.nextInt()) % positionsCandidates.length;
        positions = positionsCandidates[blkId];
        center = centerCandidates[blkId];
        color = colorCandidates[blkId];
    }

    public void shift(Direction d) {
        // TODO: そっちには動けません判定
        // TODO: 下に shift しようとしてもうこれ以上動けない場合、ステータスを返すか何かして次のブロック投入のトリガーにする
        if (isShiftable(d)) {
            switch (d) {
                case RIGHT:
                    for (int i = 0; i < positions.length; i++) {
                        positions[i][0] += 1;
                    }
                    center[0] += 1;
                    break;
                case LEFT:
                    for (int i = 0; i < positions.length; i++) {
                        positions[i][0] -= 1;
                    }
                    center[0] -= 1;
                    break;
                case DOWN:
                    for (int i = 0; i < positions.length; i++) {
                        positions[i][1] += 1;
                    }
                    center[1] += 1;
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
            for (int i = 0; i < positions.length; i++) {
                int xTmp = positions[i][0];
                int yTmp = positions[i][1];
                positions[i][0] = -yTmp + center[1] + center[0];
                positions[i][1] = xTmp - center[0] + center[1];
            }
        }
    }

    public boolean isRotatable() {
        return true;
    }

    public enum Direction {LEFT, RIGHT, DOWN}
}
