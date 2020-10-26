package com.guquan.tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGet {

 void getImage() {
     try {
         BufferedImage image = ImageIO.read(new File("G:/tank/src/images/bulletD.gif"));
         BufferedImage image2 = ImageIO.read(ImageGet.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

          //this.getClass() 来代替ImageGet.class
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
