package com.game;

import javax.swing.*;
import java.awt.*;

public class StartGame {

    public static void main(String[] args) {
        JFrame jf = new JFrame();//创建一个窗口
        jf.setTitle("贪吃蛇");
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        jf.setBounds((width-800)/2,(height-800)/2,800,800);//设置窗口出现的位置
        jf.setResizable(false);//设置窗体大小不可调节
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);

        GamePanel gp = new GamePanel();
        jf.add(gp);


    }
}
