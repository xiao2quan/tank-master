package com.guquan.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    //坦克应该有自己的属性
    private int x, y;
    private Dir dir = Dir.DOWN;
    private int SPEED = 1;
    private boolean moving = true;
    private boolean living = true;
    private Random random = new Random();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private Group group = Group.BAD;
    public static int WIDTH = ResourceMgr.tankD.getWidth(), HEIGHT = ResourceMgr.tankD.getHeight();
    public TankFrame tf = null;
    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }



    //当设置了自己的属性后，肯定要给出构造方法来给外部进行使用
    public Tank(int x,int y,Group group,Dir dir,TankFrame tf){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }
    // 让tank自己来绘制自己
    public void paint(Graphics g){
//        Color c = g.getColor();
//        g.setColor(Color.YELLOW);
//        g.fillRect(x,y,50,50);// 用小黑框代替tank
//        g.setColor(c);
        if(!living) tf.enemyTanks.remove(this);
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            default:
                break;
        }

        //根据方向进行移动
        move();

    }

    private void move(){
        if(!moving) return;
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
        if(random.nextInt(10) > 8) this.fire();
//        randomDir();
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }
    public void fire(){
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        //不能直接new 一个子弹出来，因为没有办法传递给tankframe中的bullet，因为直有tankframe才能画子弹
        // 面向对象，除了封装，还有引用
        tf.bullets.add(new Bullet(bX,bY,this.group,this.dir,this.tf));
    }
    public void die(){
        this.living = false;
    }
}
