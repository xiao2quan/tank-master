package com.guquan.tank;

import java.awt.*;

public class Bullet {
    private final int SPEED = 10;
    private Dir dir;
    private int x,y;
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();
    public static int WIDTH = ResourceMgr.bulletD.getWidth(), HEIGHT = ResourceMgr.bulletD.getHeight();
    private boolean living = true;
    TankFrame tf = null;

    public  Bullet(int x,int y,Group group ,Dir dir,TankFrame tf){
        this.x = x;
        this.y = y;
        this.group = group;
        this.dir = dir;
        this.tf = tf;

        //
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

    }

    // 让Bullet自己来绘制自己
    public void paint(Graphics g){
        if(!living)
            tf.bullets.remove(this);
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            default:
                break;
        }
        //根据方向进行移动
        move();

    }
    private void move(){
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
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGTH)
            living = false;
        rect.x = this.x;
        rect.y = this.y;

    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void collide(Tank tank){
        if(this.group == tank.getGroup()) return;
        // TODO:用一个rect来记录子弹的位置，这里循环一次new一个rect，new出来太多了
        // 应该在子弹类和tank类里面，设置rect
        if(rect.intersects(tank.rectTank)){
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH/2 -Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 -Explode.HEIGHT/2;
//            tf.explodes.add(new Explode(x,y,tf));
            tf.explodes.add(new Explode( eX,eY,tf));
        }

    }

    public void die(){
        this.living = false;
    }

}
