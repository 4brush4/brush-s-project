package com.my.test;

import com.brush.onj.BodyObj;
import com.brush.onj.FoodObj;
import com.brush.onj.HeadObj;
import com.brush.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SnackGame extends JFrame {


    //游戏状态 0.未开始 1.游戏中 2.暂停 3.失败 4.通关 5.失败后重新开始 6.游戏通过,下一关
    public static  int state = 0;
    //分数
    public int score = 0;
    //定义双缓存图片(解决屏幕频闪问题)
    Image offScreenImage = null;
    //窗口宽高
    int winWidth = 800;
    int winHeight = 600;

    //蛇头对象
    HeadObj headObj = new HeadObj(GameUtils.rightImg,60,510,this);

    //蛇的身体集合
    public List<BodyObj> bodyObjList = new ArrayList<>();

    //食物
    public FoodObj foodObj = new FoodObj().getFood();


    public void launch(){

        this.setVisible(true);

        this.setSize(winWidth,winHeight);

        this.setTitle("贪吃蛇游戏");

        this.setLocationRelativeTo(null);

        //蛇身体的初始化
        bodyObjList.add(new BodyObj(GameUtils.foodImg,30,570,this));
        bodyObjList.add(new BodyObj(GameUtils.foodImg,0,570,this));

        //键盘事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    switch (state){
                        case 0:
                            //未开始
                            state = 1;
                            break;
                        case 1:
                            //游戏中
                            state = 2;
                            repaint();
                            break;
                        case 2:
                            //游戏暂停
                            state = 1;
                            break;
                        case 3:
                            //失败后重新开始
                            state = 5;
                            break;
                        case 4:
                            //下一关
                            state = 6;
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        while(true){
            if(state == 1){
                //游戏中才调用
                repaint();
            }
            //失败后重新开始
            if(state == 5){
                state = 0;
                resetGame();
            }
            //通关开启下一关
            if(state == 6 && GameUtils.level != 3){
                state = 1;
                GameUtils.level++;
                resetGame();
            }
            try {
                //1秒100毫秒
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void paint(Graphics g) {
        //初始化双缓存图片(解决屏幕频闪问题)
        if (offScreenImage == null){
            offScreenImage = this.createImage(winWidth,winHeight);
        }
        //获取图片对应的graphics对象
        Graphics gImage = offScreenImage.getGraphics();
        //灰色背景
        gImage.setColor(Color.gray);
        gImage.fillRect(0, 0, winWidth, winHeight);
        //网格线
        gImage.setColor(Color.black);
        //横线
        for (int i = 0; i <= 20; i++) {
            gImage.drawLine(0, i * 30, 600, i * 30);

        }
        //竖线
        for (int i = 0; i <= 20; i++) {
            gImage.drawLine(i*30,0,i*30,600);

        }
        //绘制蛇身体
        for (int i = bodyObjList.size() - 1; i >=0; i--) {
            bodyObjList.get(i).panitSelf(gImage);
        }
        //绘制蛇头
        headObj.panitSelf(gImage);
        //绘制食物
        foodObj.panitSelf(gImage);
        //关卡设置
        GameUtils.drawWord(gImage,"第"+GameUtils.level+"关",Color.cyan,40,650,260);
        //分数绘制
        GameUtils.drawWord(gImage,score +" 分",Color.BLUE,50,650,330);
        //绘制提示语
        gImage.setColor(Color.gray);
        prompt(gImage);
        //将双缓存图片绘制到窗口当中
        g.drawImage(offScreenImage,0,0,null);
    }


    //绘制提示语
    void prompt(Graphics g){
        //未开始
        if(state == 0){
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"按下空格键开始游戏",Color.yellow,35,150,290);
        }
        //暂停
        if(state == 2){
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"按下空格键继续游戏",Color.yellow,35,150,290);
        }
        //通关
        if(state == 4){
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"达成条件,游戏通关",Color.green,35,150,290);
        }
        //游戏失败
        if(state == 3){
            g.fillRect(120,240,400,70);
            if(GameUtils.level == 3){
                GameUtils.drawWord(g,"达成条件,游戏通关",Color.red,35,150,290);
            }else {
                GameUtils.drawWord(g,"本关通过,点击空格下一关",Color.red,35,150,290);
            }
        }
    }


    //游戏重置
    void resetGame(){
        //关闭当前窗口
        this.dispose();
        //开启一个新窗口
        String[] args = {};
        main(args);
    }

    public static void main(String[] args) {

        SnackGame snackGame = new SnackGame();
        snackGame.launch();

    }
}
