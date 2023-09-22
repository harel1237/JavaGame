package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    int[][] mapTileNum;

    public TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[10];

        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/maps/map.txt");
    }



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


    public void loadMap(String path){

        try{
            InputStream inputStream = getClass().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));



            int col =0;
            int row =0;

            // map un-packer

            while(col<gp.maxScreenCol && row< gp.maxScreenRow-2) {


                String line = bufferedReader.readLine();

                while(col< gp.maxScreenCol){

                    System.out.println(line);

                    String[] numbers = line.split(",");


                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                col =0;
                row++;
            }

            bufferedReader.close();
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


            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
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
