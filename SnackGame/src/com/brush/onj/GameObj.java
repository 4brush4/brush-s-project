package com.brush.onj;

import com.my.test.SnackGame;

import java.awt.*;

public class GameObj {

    //图片
    Image img;
    //坐标
    int x;
    int y;
    //宽高
    int width = 30;
    int height = 30;
    //窗口类的引用
    SnackGame frame;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public SnackGame getFrame() {
        return frame;
    }

    public void setFrame(SnackGame frame) {
        this.frame = frame;
    }

    public GameObj(){
    }

    public GameObj(Image img, int x, int y, SnackGame frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.frame = frame;
    }

    public GameObj(Image img, int x, int y, int width, int height, SnackGame frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frame = frame;
    }

    //绘制自身的方法
    public void panitSelf(Graphics g){
        g.drawImage(img,x,y,null);
    }
}
