package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {

    //定义游戏的开始时间
    long startTime;

    //定义游戏的结束时间
    long endTime;

    //定义result;
    long result;


    //定义飞机的X坐标
    int planeX;

    //定义飞机的Y坐标
    int planeY;

    //定义数组，存储10个炸弹的X轴坐标
    int[] shellXs = new int[10];

    //定义数组，存储10个炸弹的Y轴坐标
    int[] shellYs = new int[10];

    //定义飞机飞的方向
    boolean left,right,up,down;

    //定义游戏是否开始，默认是暂停的
    boolean isStart = false;

    //定义并存储每一个炮弹的弧度
    double[] degrees = new double[10];

    //定义飞机的生死
    boolean isDie = false;//默认没死



    public void init(){

        //给飞机X Y轴赋值
        planeX  = 400;
        planeY = 500;

        //给10个炸弹，X,Y轴上的数值
        for(int i = 0; i<10;i++){
            shellXs[i] = 100;
            shellYs[i] = 100;

            //给每一个炮弹设置随机的弧度
            degrees[i] = (int)(Math.random()*2*Math.PI);

        }
    }

    public GamePanel(){

        init();

        // 必须将焦点放在面板上
        this.setFocusable(true);


        //加入对飞机的监听
       this.addKeyListener(new KeyAdapter() {
           //当键盘按下去时候
           @Override
           public void keyPressed(KeyEvent e) {
               super.keyPressed(e);
               //获取到按下的键是哪个
               int keyCode = e.getKeyCode();
               if (keyCode == KeyEvent.VK_SPACE){//如果按下的键是空格
                   //定义开始时间
                   startTime = System.currentTimeMillis();



                   //如果飞机死了，那么游戏重新开始，坐标全部初始化
                   if(isDie){
                       init();
                       isDie = false;
                   }else{
                       isStart = !isStart;
                   }

               }

                if(keyCode == KeyEvent.VK_RIGHT){
                    right = true;
                }

                if(keyCode == KeyEvent.VK_LEFT){
                    left = true;
                }

                if(keyCode == KeyEvent.VK_DOWN){
                    down = true;
                }

                if(keyCode == KeyEvent.VK_UP){
                    up = true;
                }

           }
           // 加入监听方法，当按键抬起来的时候
           @Override
           public void keyReleased(KeyEvent e) {
               super.keyReleased(e);
               int keyCode = e.getKeyCode();
               if(keyCode == KeyEvent.VK_RIGHT){
                   right = false;
               }
               if(keyCode == KeyEvent.VK_LEFT){
                   left = false;
               }
               if(keyCode == KeyEvent.VK_UP){
                   up = false;
               }
               if(keyCode == KeyEvent.VK_DOWN){
                   down = false;
               }
           }
       });

       //初始化定时器，每50MS执行一下actionPerformed 中的逻辑，
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//游戏开始的情况下，飞机才会动
                if(isStart == true && isDie == false){
                    if(left){
                        planeX -= 8;
                    }

                    if(right){
                        planeX += 8;
                    }

                    if(up){
                        planeY -= 8;
                    }

                    if(down){
                        planeY += 8;
                    }

                    //炮弹按照弧度动
                    for(int i =0; i<10;i++){
                        //炮弹的X轴坐标动
                        shellXs[i] += Math.cos(degrees[i]) * 10;

                        //炮弹的Y轴坐标动
                        shellYs[i] += Math.sin(degrees[i]) * 10;

                        //炮弹到边界后，从另一个边界飞过来
                        //左边界
                        if(shellXs[i] <= 0){
                            shellXs[i] = 700;
                        }

                        //右边界
                        if(shellXs[i] > 700){
                            shellXs[i] = 0;
                        }

                        //上边界
                        if(shellYs[i] <= 30){
                            shellYs[i] =700;
                        }

                        //下边界
                        if(shellYs[i] >= 700){
                            shellYs[i] = 30;
                        }


                        //对每个飞机和炮弹进行检测，如果碰到一起就死了
                        boolean flag = new Rectangle(planeX,planeY,60,68).intersects(new Rectangle(shellXs[i],shellYs[i],60,60));

                        if(flag){
                            //得到死亡时间
                            endTime = System.currentTimeMillis();
                            isDie = true; //飞机死了
                        }
                    }

                    //改变完坐标后，必须刷新画面
                    repaint();
                }

            }
        });

        //定时器必须启动
         timer.start();





    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //设置背景颜色,画背景
        this.setBackground(new Color(255, 255, 255, 15));

        //设置飞机坐标，画飞机
        images.planeImg.paintIcon(this,g,planeX,planeY);

        //设置炸弹坐标，画炸弹
        for(int i = 0; i<10;i++){
            images.shellImg.paintIcon(this,g,shellXs[i],shellYs[i]);
        }

        //当游戏是暂停的，在面板中间打印一段文字
        if(isStart == false){
            g.setColor(new Color(10,10,10, 157));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("空格开始游戏",180,400);
        }

        //如果飞机死了，在面板中显示游戏结束
        if(isDie &&((endTime-startTime)/1000)<60){
            g.setColor(new Color(10,10,10, 157));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString(" 游戏结束！存活了"+(endTime-startTime)/1000+"秒",180,400);
            g.drawString(" 你是个快枪手",180,500);
        }

        if(isDie &&((endTime-startTime)/1000)>60){
            g.setColor(new Color(10,10,10, 157));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString(" 游戏结束！存活了"+(endTime-startTime)/1000+"秒",180,400);
            g.drawString(" 你是个持久性选手",180,500);
        }



    }
}
