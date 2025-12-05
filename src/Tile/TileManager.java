package Tile;

import Main.GamePanel;
import util.FileIO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    FileIO io;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp, FileIO io){
        this.io = io;
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCollum][gp.maxScreenRow];
        getTileImage();
        loadMap("/util/maps/map.txt");
    }
    public void getTileImage(){

            tile[0] = new Tile();
            tile[0].image = io.readImage("/Tiles/floor.png");

            tile[1] = new Tile();
            tile[1].image = io.readImage("/tiles/wall.png");

            tile[2] = new Tile();
            tile[2].image = io.readImage("/tiles/water.png");

        }

        public void loadMap(String filePath) {
            mapTileNum = io.readLocationMapFile(filePath);
        }


        public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col< gp.maxScreenCollum && row< gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image,x,y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCollum){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;

            }
        }
        }
    }

