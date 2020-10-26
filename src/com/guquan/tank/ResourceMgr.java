package com.guquan.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceMgr {
//    public  static BufferedImage tankL = null,tankU = null,tankR = null,tankD = null;
    public  static BufferedImage tankL ,tankU ,tankR ,tankD ;
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];
    // 静态语句块
    static {
        try {
             tankL = ImageIO.read(ImageGet.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
             tankU = ImageIO.read(ImageGet.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
             tankR = ImageIO.read(ImageGet.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
             tankD = ImageIO.read(ImageGet.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
             bulletL = ImageIO.read(ImageGet.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
             bulletU = ImageIO.read(ImageGet.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
             bulletR = ImageIO.read(ImageGet.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
             bulletD = ImageIO.read(ImageGet.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
             for(int i = 1;i<16;i++) explodes[i] = ImageIO.read(ImageGet.class.getClassLoader().getResourceAsStream("images/e"+ (i+1) +".gif"));
            //可以用this.getClass() 来代替ImageGet.class
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
