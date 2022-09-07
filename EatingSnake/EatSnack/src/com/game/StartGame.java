package com.game;

import javax.swing.*;
import java.awt.*;

public class StartGame {

    int width;
    int height;


    public static void main(String[] args) {
        //创建一个窗体
        JFrame jf = new JFrame();
        jf.setTitle("贪吃蛇 ");
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        jf.setBounds((width-800)/2,(height-800)/2,800,800);

        jf.setResizable(false);//设置窗口大小不可调节

        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//当关闭窗口，程序也随之结束运行

        jf.setVisible(true);//让屏幕可视化

        //创建面板
        GamePanel gp = new GamePanel();
        //将面板装入窗体
        jf.add(gp);



    }
}
