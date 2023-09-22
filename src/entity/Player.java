package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends entity{

    GamePanel gp;
    KeyHandler keyH;


    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        setDeafultValues();
        getPlayerImage();
    }


    public void setDeafultValues() {
        x=100;
        y=100;
        speed =4;
        direction = "down";

        int maxX = gp.getWidth()* gp.tileSize;
        int maxY = gp.getHeight()*gp.tileSize;
    }

    public void getPlayerImage() {

        try
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up_sprite1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up_sprite2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down_sprite1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down_sprite2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/left_sprite1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/left_sprite2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/right_image1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/right_image2.png"));


        }


        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {


        if(keyH.upPressed == true) {

            direction = "up";
            if(y>0) {
                y -= speed;
                updateSprite();
            }
        }
        else if(keyH.downPressed == true) {
            direction = "down";
            if(y<gp.getHeight()- gp.tileSize) {
                y += speed;
                updateSprite();
            }
        }
        else if(keyH.leftPressed == true) {
            direction = "left";
            if(x>0) {
                x -= speed;
                updateSprite();
            }
        }
        else if(keyH.rightPressed == true) {
            direction = "right";
            if(x<gp.getWidth()-(16*3)) {
                x += speed;
                updateSprite();
            }
        }

        else {
            spriteNum=1;
        }
    }


    public void updateSprite() {
        if(spriteCounter>10) {
            if(spriteNum ==1) {
                spriteNum = 2;
            }
            else if(spriteNum ==2) {
                spriteNum = 1;
            }
            spriteCounter =0;
        }
        spriteCounter ++;
    }

    public void draw(Graphics2D g2) {


        BufferedImage image = null;

        switch(direction) {

            case "up":

                if(spriteNum ==1)
                    image = up1;
                else
                    image = up2;

                break;


            case "down":
                if(spriteNum ==1)
                    image = down1;
                else
                    image = down2;

                break;

            case "left":
                if(spriteNum ==1)
                    image = left1;
                else
                    image = left2;
                break;


            case "right":
                if(spriteNum ==1)
                    image = right1;
                else
                    image = right2;

                break;


        }

        g2.drawImage(image, x,y,gp.tileSize,gp.tileSize,null);
    }

}