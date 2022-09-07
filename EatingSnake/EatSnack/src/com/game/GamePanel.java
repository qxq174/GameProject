package com.game;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel {//继承了JPanel以后，才具备面板的功能，才成为一个面板
    //定义2个数组
    //1个数组专门存储蛇的X坐标
    int[] snakeX = new int[200];
    //1个数组专门存储蛇的Y坐标
    int[] snakeY = new int[200];

    //蛇的长度
    int length;
    //定义蛇的行走方向
    String direction;

    //游戏只有2个状态，开始，暂停
    boolean isStart = false;//游戏默认暂停的

    // 加入一个定时器
    Timer timer;

    //定义食物的X,Y轴
    int foodX;
    int foodY;

    //定义积分
    int score = 0;

    //加入变量，判断小蛇生死
    boolean isDie = false;//默认情况下，小蛇没有死


    public void init(){
        //初始化蛇的长度
        length = 3;
        //初始化蛇头坐标
        snakeX[0] = 200;
        snakeY[0] = 275;

        //初始化第一节身子坐标
        snakeX[1] = 175;
        snakeY[1] = 275;

        //初始化第二节身子坐标
        snakeX[2] = 150;
        snakeY[2] = 275;

        // 初始化蛇头的方向
        direction = "R";

        foodX = 300;
        foodY = 200;

    }

    public GamePanel(){//构造器
        init();
        //将焦点定位在当前操作面板上
        this.setFocusable(true);

        // 加入监听
        this.addKeyListener(new KeyAdapter() {//监听键盘按键的按下操作，监听方法

            @Override
            public void keyPressed(KeyEvent e) {//监听按下的是哪个键
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_SPACE){//如果按了空格
                    if(isDie){//小蛇死亡了，全部恢复到初始状态
                        init();
                        isDie = false;

                    }else{
                        isStart = !isStart;
                        repaint();
                    }


                }
                //监听向上监听
                if(keyCode == KeyEvent.VK_UP){
                    direction = "U";
                }

                if(keyCode == KeyEvent.VK_DOWN){
                    direction = "D";
                }

                if(keyCode == KeyEvent.VK_RIGHT){
                    direction = "R";
                }

                if(keyCode == KeyEvent.VK_LEFT){
                    direction = "L";
                }
            }
        });

        //对定时器进行初始化的动作
        timer = new Timer(100, new ActionListener() {
            /*
            ActionListener是事件监听
            相当于每100MS监听一下你是否发生了一个动作
            具体的动作放入actionPerformed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStart && isDie == false){// 游戏是 开始状态，蛇才动,并且蛇没有死
                    //后一节生在走到前一节身子的位置上
                    for(int i=length -1;i>0;i--){
                        snakeX[i] = snakeX[i-1];
                        snakeY[i] = snakeY[i-1];
                    }
                    // 动蛇头
                    if("R".equals(direction)){
                        snakeX[0] += 25;//向右动
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

                    //防止蛇超出边界
                    if(snakeX[0] > 750){
                        snakeX[0] = 25;
                    }
                    if(snakeX[0] < 25){
                        snakeX[0] = 750;
                    }
                    if(snakeY[0] < 100){
                        snakeY[0] = 725;
                    }
                    if(snakeY[0] > 725){
                        snakeY[0] =100;
                    }

                    //检测碰撞的动作
                    //食物的坐标和蛇头的坐标一样的时候，才是碰撞了
                    if((snakeX[0] == foodX && snakeY[0] == foodY) ){
                        //蛇长度加1
                        length++;
                        //x坐标必须是25的倍数， 25~750之间
                        //Math.random() -> 0.0~1.0
                        //Math.random()*30 -> 0.0~30.0
                        //(int)(Math.random()*30) -> 0~29
                        //((int)(Math.random()*30))+1 -> 1~30
                        foodX = ((int)(Math.random()*30)+1)*25;
                        //y坐标必须在100~725 -> [4,29]*25
                        //[4,29] ->[0,25]+4
                        //new Random().nextInt(26) -> [0,26] ->[0,25]
                        foodY = (new Random().nextInt(26)+4)*25;
                        //吃上食物后，积分加10
                        score += 10;
                    }
                    //死亡判定：如果蛇头碰到蛇身，那么就死了
                     for(int i = 1;i < length; i++){
                         if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                             isDie = true;
                         }
                     }


                    repaint();//重绘



                }


            }
        });
        timer.start();//定时器必须要启动


    }




    //paintComponent这个方法比较特殊，这个方法属于图形版的MAIN方法
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(206, 240, 245));

        //画头部图片
        images.titleImg.paintIcon(this,g,10,10);//对应四个参数,g是画笔
        //画一个矩形
       // g.setColor(new Color(255, 255, 255, 255));//调节画笔颜色
        //g.fillRect(10,70,770,685);//在窗体上加一个白色子窗口

        //画小蛇
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



       for (int i =1; i<length;i++){
           images.bodyImg.paintIcon(this,g,snakeX[i],snakeY[i]);
       }

       //如果游戏状态是暂停的，那么界面中间有一句提示语
        if(isStart == false){

            g.setColor(new Color(1,1,1));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("点击空格开始游戏",250,330);

        }

        //画食物
        images.foodImg.paintIcon(this,g,foodX,foodY);

        //画积分
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("积分："+score,620,40);

        //画进入死亡状态
        if(isDie){
            g.setColor(new Color(255,82,68));
            g.setFont(new Font("微软雅黑",Font.BOLD,20));
            g.drawString("小蛇死亡，游戏结束，按下空格重新开始",250,330);
        }

    }


}
