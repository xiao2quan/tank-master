package com.guquan.tank;

import java.awt.*;

public class Explode {

    private int x, y;

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int WIDTH = ResourceMgr.explodes[1].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[1].getHeight();
    private boolean living = true;
    TankFrame tf = null;
    private int step = 0;

    public Explode(int x, int y,  TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Audio("audio/explode.wav").play();
    }

    // 让Bullet自己来绘制自己
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step >=ResourceMgr.explodes.length)
        tf.explodes.remove(this);
    }
}


