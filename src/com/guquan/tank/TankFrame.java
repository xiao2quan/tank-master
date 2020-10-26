package com.guquan.tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


// 这个代码让我们了解继承Frame类，并且这个TankFrame不需要在main函数中
// 为什么要继承frame呢，继承的好处是，可以对Frame一些方法进行重写
public class TankFrame extends Frame {
    Tank myTank = new Tank(200,400,Group.GOOD,Dir.DOWN,this);
    //当时没有导入util包时提示不能带入参数，这个是多态的概念，多了解多态的知识
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> enemyTanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
//    Explode e = new Explode(100,100,this);

    static final int GAME_WIDTH = 1080,GAME_HEIGTH = 960;

    private int SPEED = 10;
    //这里自己就是一个窗口，把自己显示出来就行了
    public TankFrame(){
        setVisible(true);//设置窗口可见
        setSize(GAME_WIDTH,GAME_HEIGTH);//设置窗口的大小
        setResizable(false);//能够改变大小
        setTitle("tank war");// 设置标题
        // 添加按键监听
        this.addKeyListener(new MyKeyListener());
        // 加一个Window监听器，监听着窗口上的×，用于关闭窗口，这里用到了WindowEvent
        // 这里面是匿名类，传的是WindowAdapter，重写里面的方法
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // 游戏中的一个概念，双缓冲
    // 屏幕刷新的特别快，绘画跟不上，所以会闪烁。先在内存中定义大小一样的图片并绘画好数据，然后一次性显示，这样可以解决闪烁
    //首先定义一个和屏幕大小一样的，图片上也有画笔，把背景画出来
    //把画笔交给了tank，所以画笔是直接画在刚刚定义的图片上的，所以tank和子弹都是画在了内存中。
    //画好了tank和子弹，最后一次性画在屏幕上。
    Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGTH);
        }
        Graphics goffScreen = offScreenImage.getGraphics();
        Color c = goffScreen.getColor();
        goffScreen.setColor(Color.BLACK);
        goffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGTH);
        goffScreen.setColor(c);
        paint(goffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }
    @Override
    public void paint(Graphics g){
        //这里肯定不是把tank属性x y dir拿出来，这不是封装
        //tank自己肯定是最知道应该怎么画出tank的
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullet num:" + bullets.size(),10,60);
        g.drawString("enemy tanks num:" + enemyTanks.size(),10,80);
        g.drawString("explode num:" + enemyTanks.size(),10,100);

        g.setColor(c);//恢复现场，就是保存原来的颜色
        myTank.paint(g);
//        for(Bullet b : bullets)
//        //迭代器添加，只能在这个for循环里删除，别的地方不可以删除，所以有bug
//            b.paint(g);// 这种方法会有bug，具体bug看有道问题记录 1
//        }
        // 这里只管循环paint，此外还可以用迭代器进行删除 iterator
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).paint(g);
        }
        //这儿是创建敌方tank
        for(int i = 0; i < enemyTanks.size(); i++){
            enemyTanks.get(i).paint(g);
        }
        // 画出爆炸
        for(int i = 0; i < explodes.size(); i++){
            explodes.get(i).paint(g);
        }
        // 检测子弹和tank碰撞
        for(int i = 0; i < bullets.size();i++){
            for(int j = 0;j < enemyTanks.size();j++)
                bullets.get(i).collide(enemyTanks.get(j));
        }
    }



    class MyKeyListener extends KeyAdapter{
        boolean bl = false;
        boolean bu = false;
        boolean br = false;
        boolean bd = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int Key = e.getKeyCode();
            switch (Key){
                case KeyEvent.VK_LEFT:
                    bl = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int Key = e.getKeyCode();
            switch (Key){
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                        break;
                default:
                    break;
            }
            setMainTankDir();
        }
        private void setMainTankDir(){
            if(!bl && !bu && !br && !bd) myTank.setMoving(false);
            else {
                myTank.setMoving(true);
                if (bl) myTank.setDir(Dir.LEFT);
                if (bu) myTank.setDir(Dir.UP);
                if (br) myTank.setDir(Dir.RIGHT);
                if (bd) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
