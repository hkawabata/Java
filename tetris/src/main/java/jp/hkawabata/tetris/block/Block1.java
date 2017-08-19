package jp.hkawabata.tetris.block;

import java.awt.*;
import java.util.Properties;

/**
 * Created by kawabatahiroto on 2017/08/19.
 */
public class Block1 extends IBlock {
    public Block1() {
        xPositions = new int[]{0, 0, 0, 0};
        yPositions = new int[]{0, 1, 2, 3};
        xCenter = 0;
        yCenter = 1;
        color = Color.CYAN;
    }
}
