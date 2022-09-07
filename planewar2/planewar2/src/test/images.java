package test;

import javax.swing.*;
import java.net.URL;

public class images {

    static URL shellURL = images.class.getResource("/images/shell.png");
    static ImageIcon shellImg = new ImageIcon(shellURL);

    static URL planeURL = images.class.getResource("/images/plane.png");
    static ImageIcon planeImg = new ImageIcon(planeURL);
}
