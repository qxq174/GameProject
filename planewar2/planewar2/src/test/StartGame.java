package test;

import javax.swing.*;
import java.awt.*;

public class StartGame {

    public static void main(String[] args) {
        JFrame jf = new JFrame();

        jf.setTitle("飞机大战");

        //求出屏幕宽高
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        jf.setBounds((width-600)/2,(height-800)/2,600,800);

        jf.setVisible(true);

        jf.setResizable(false);

        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GamePanel gp = new GamePanel();
        jf.add(gp);
    }
}
