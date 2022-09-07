package com.game;

import javax.swing.*;
import java.net.URL;

public class images {
    //封装图片
    public static URL bodyURL =  images.class.getResource("/images/body.png");
    public static ImageIcon bodyImg = new ImageIcon(bodyURL);

    public static URL downURL =  images.class.getResource("/images/down.png");
    public static ImageIcon downImg = new ImageIcon(downURL);

    public static URL foodURL =  images.class.getResource("/images/food.png");
    public static ImageIcon foodImg = new ImageIcon(foodURL);

    public static URL leftURL =  images.class.getResource("/images/left.png");
    public static ImageIcon leftImg = new ImageIcon(leftURL);

    public static URL rightURL =  images.class.getResource("/images/right.png");
    public static ImageIcon rightImg = new ImageIcon(rightURL);

    public static URL titleURL =  images.class.getResource("/images/title.jpg");
    public static ImageIcon titleImg = new ImageIcon(titleURL);

    public static URL upURL =  images.class.getResource("/images/up.png");
    public static ImageIcon upImg = new ImageIcon(upURL);








}
