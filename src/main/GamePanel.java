package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    //Screen Settings
    final int originalTileSize = 16;

    final int scale = 3;

    public final int tileSize = originalTileSize * scale;

    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    int screenWidth = tileSize * maxScreenCol;
    int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();

    Thread gameThread;

    Player player = new Player(this , keyH);
    TileManager tileManager = new TileManager(this);

    //Default pos:



    public GamePanel () {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //For FPS

        long timer = 0;
        long drawCount =0;



        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta +=(currentTime - lastTime) / drawInterval; //Time.deltaTime in Unity
            timer +=(currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta --;
                drawCount ++;
            }

            if(timer >= 1000000000 ) {
                System.out.println("FPS:"+ drawCount);
                drawCount =0;
                timer =0;

            }

        }

    }



    public void update() {


        player.update();


    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

//		screenWidth = this.getWidth();
//		screenHeight = this.getHeight();
//
//		maxScreenRow = 1 + screenHeight/tileSize;
//		maxScreenCol = 1 + screenWidth/tileSize;

        Graphics2D g2 = (Graphics2D)g;

        tileManager.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}
