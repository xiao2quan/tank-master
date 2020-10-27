package com.guquan.tank;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Tank_main {

    public static void main(String[] args) throws Exception {
        //frame 代表一个窗口，下面几句代码带你初步认识Frame
        //Frame f = new Frame();
        // 怎么把窗口显示出来，就需要调用这个构造方法
        TankFrame tf = new TankFrame();
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        //初始化敌方tank
        for(int i = 0; i < initTankCount; i++){
            tf.enemyTanks.add(new Tank(50 + i*60,200,Group.BAD, Dir.DOWN,tf));

        }
        while(true){
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
