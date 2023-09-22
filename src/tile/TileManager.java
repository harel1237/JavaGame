package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int[][] map;

    public TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[10];

        getTileImage();

        //readMap("/maps/map.txt");
    }

//    public void readMap(String filePath) {
//
//        try {
//
//
//
// aa
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


    public void getTileImage(){

        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile1.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile2.png"));



        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2){

        int col =0;
        int row = 0;

        int x =0;
        int y =0;


        while(col < gp.maxScreenCol && row < gp.maxScreenRow ) {

            g2.drawImage(tile[0].image,x,y,gp.tileSize,gp.tileSize,null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){

                col=0;
                row++;
                x=0;

                y+= gp.tileSize;
            }
        }
    }
}
