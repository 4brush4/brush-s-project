package com.brush.onj;

import com.my.test.SnackGame;

import java.awt.*;

public class BodyObj extends GameObj{
    @Override
    public void panitSelf(Graphics g) {
        super.panitSelf(g);
    }

    public BodyObj(Image img, int x, int y, SnackGame frame) {
        super(img, x, y, frame);
    }
}
