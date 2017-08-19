package jp.hkawabata.tetris;

import java.awt.*;
import java.util.Properties;
import java.util.Random;

/**
 * Created by kawabatahiroto on 2017/08/19.
 */
public class Block {
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

        positions = new int[positionsCandidates[blkId].length][2];
        for (int i = 0; i < positionsCandidates[blkId].length; i++) {
            positions[i][0] = positionsCandidates[blkId][i][0];
            positions[i][1] = positionsCandidates[blkId][i][1];
        }
        center = new int[]{centerCandidates[blkId][0], centerCandidates[blkId][1]};
        color = colorCandidates[blkId];
    }

    public void shift(Direction d) {
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

    public void rotate() {
        for (int i = 0; i < positions.length; i++) {
            int xTmp = positions[i][0];
            int yTmp = positions[i][1];
            positions[i][0] = -yTmp + center[1] + center[0];
            positions[i][1] = xTmp - center[0] + center[1];
        }
    }

    public enum Direction {LEFT, RIGHT, DOWN}
}
