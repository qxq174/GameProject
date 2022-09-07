package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel {

    //游戏状态
    boolean isStart = false; ///游戏默认是暂停的

    //建立蛇X坐标，用数组存储
    int[] snakeX = new int[200];

    //建立蛇Y坐标，用数组存储
    int[] snakeY = new int[200];

    //设置蛇的长度
    int length;

    //设置食物的坐标
    int foodX;
    int foodY;

    //蛇头的方向
    String direction;

    //定义定时器
    Timer timer;

    //定义生死
    boolean isDie = false;//默认情况下小蛇没有死

    //定义积分
     int score;
    public void init(){//初始化方法
        //蛇的原本长度为3
        length = 3;

        //设置蛇头的初始位置
        snakeX[0] = 200;
        snakeY[0] = 275;

        //设置蛇身位置
        snakeX[1] = 175;
        snakeY[1] = 275;

        snakeX[2] = 150;
        snakeY[2] = 275;

        //初始化蛇头的方向
        direction = "R";

        foodX = 100;
        foodY = 200;

        score = 0;
    }




    public GamePanel(){//构造方法

        init();
        this.setFocusable(true);//监听键盘按键按下的操作，监听方法

        //加入监听
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_SPACE){{
                    if(isDie == true){
                        init();
                        isDie = false;
                    }else{
                        isStart = !isStart;
                        repaint();
                    }
                }

                }



                if(keyCode == KeyEvent.VK_RIGHT){
                    direction = "R";
                }

                if(keyCode == KeyEvent.VK_LEFT){
                    direction = "L";
                }

                if(keyCode == KeyEvent.VK_DOWN){
                    direction = "D";
                }

                if(keyCode == KeyEvent.VK_UP){
                    direction = "U";
                }

            }
        });

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStart && isDie == false){

                    for(int i = length-1;i>0;i--){
                        snakeX[i] = snakeX[i-1];
                        snakeY[i] = snakeY[i-1];
                    }

                    if("R".equals(direction)){
                        snakeX[0] += 25;
                    }

                    if("L".equals(direction)){
                        snakeX[0] -= 25;
                    }

                    if("U".equals(direction)){
                        snakeY[0] -= 25;
                    }

                    if("D".equals(direction)){
                        snakeY[0] += 25;
                    }

                    // 防止蛇出边界
                    if(snakeX[0] > 750 ){
                        snakeX[0] =25;
                    }

                    if(snakeX[0] < 25){
                        snakeX[0] = 750;
                    }

                    if(snakeY[0] < 100){
                        snakeY[0] = 725;
                    }

                    if (snakeY[0] > 725) {
                        snakeY[0] = 100;
                    }


                    //检测碰撞，如果吃到了食物，
                    if(snakeX[0] == foodX &&snakeY[0] ==foodY){
                        length = length + 1;
                        //让食物随机出现
                        foodX = ((int)(Math.random()*30)+1)*25;
                        foodY =(new Random().nextInt(26)+4)*25;

                        score += 10;
                    }

                    //死亡判定，如果蛇头碰到任意蛇身就死了
                    for(int i = 1; i<length;i++){
                        if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                            isDie = true;

                        }
                    }

                    repaint();
                }
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(206,240,245));//设置背景
        images.titleImg.paintIcon(this,g,10,10);//设置头部图片



        //根据方向画蛇头
        if("R".equals(direction)){
            images.rightImg.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        if("L".equals(direction)){
            images.leftImg.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        if("U".equals(direction)){
            images.upImg.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if("D".equals(direction)){
            images.downImg.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        for(int i = 1; i<length;i++){// 画身子
            images.bodyImg.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        if(isStart == false){//当游戏是暂停的时候，显示一句话
            g.setColor(new Color(0,0,0));
            g.setFont(new Font("微软雅黑", Font.BOLD,40));
            g.drawString("单击空格开始游戏",250,330);
        }

        if(isDie == true){
            g.setColor(new Color(0,0,0));
            g.setFont(new Font("微软雅黑", Font.BOLD,40));
            g.drawString("游戏结束！",250,330);
        }

        //画积分
        g.setColor(new Color(255,255,255));
        g.setFont(new Font("微软黑雅",Font.BOLD,20));
        g.drawString("积分"+score,620,40);


        //画食物
        images.foodImg.paintIcon(this,g,foodX,foodY);

    }
}
