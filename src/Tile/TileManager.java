    package Tile;

    import Main.GamePanel;
    import Main.KeyHandler;
    import util.FileIO;
    import util.SoundSystem;

    import javax.imageio.ImageIO;
    import java.awt.*;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.Objects;

    public class TileManager {

        GamePanel gp;
        FileIO util = new FileIO(gp);
        KeyHandler keyH;
        public Tile[] tile;
        public int mapTileNum[][];
        ArrayList<String> fileNames = new ArrayList<>();
        ArrayList<String> collisionStatus = new ArrayList<>();



    public String mapName = "/tile/maps/levelOne.txt";
    public String mapMusic = "Resources/musicFiles/mainTheme.wav";

        public TileManager(GamePanel gp, FileIO io){
            this.gp = gp;
            this.io = io;

        tile = new Tile[132];
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];   // RIGTIG ORIENTERING
        getTileImage();

    }

    public void getTileImage() {

        if(!Objects.equals(mapName, "/tiles/maps/BlueMap")) {
            try {
                tile[0] = new Tile();
                tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor.png"));
        tile[0] = new Tile();
        tile[0].image = io.readImage("/tile/tiles/floor.png");

        tile[1] = new Tile();
        tile[1].image = io.readImage("/tile//tiles/wall.png");
        tile[1].collision = true;

        tile[2] = new Tile();
        tile[2].image = io.readImage("/tile//tiles/water.png");
        tile[2].collision = true;

        tile[3] = new Tile();
        tile[3].image = io.readImage("/tile//tiles/grass.png");

        tile[4] = new Tile();
        tile[4].image = io.readImage("/tile//tiles/tree.png");
        tile[4].collision = true;

        tile[5] = new Tile();
        tile[5].image = io.readImage("/tile//tiles/road.png");

        tile[6] = new Tile();
        tile[6].image = io.readImage("/tile//tiles/lavaGulv.png");

        tile[7] = new Tile();
        tile[7].image = io.readImage("/tile//tiles/tile_49.png");

        tile[8] = new Tile();
        tile[8].image = io.readImage("/tile//tiles/tile_34.png");
        tile[8].collision = true;

        tile[9] = new Tile();
        tile[9].image = io.readImage("/tile//tiles/tile_0_0.png");
        tile[9].collision = true;

        tile[10] = new Tile();
        tile[10].image = io.readImage("/tile//tiles/tile_0_1.png");

        tile[11] = new Tile();
        tile[11].image = io.readImage("/tile//tiles/tile_2_2.png");

        tile[12] = new Tile();
        tile[12].image = io.readImage("/tile//tiles/tile_2_0.png");

        tile[13] = new Tile();
        tile[13].image = io.readImage("/tile//tiles/tile_2_1.png");

        tile[14] = new Tile();
        tile[14].image = io.readImage("/tile//tiles/tile_3_0.png");

        tile[15] = new Tile();
        tile[15].image = io.readImage("/tile//tiles/tile_2_3.png");

        tile[16] = new Tile();
        tile[16].image = io.readImage("/tile//tiles/tile_0_2.png");

        tile[17] = new Tile();
        tile[17].image = io.readImage("/tile//tiles/tile_0_3.png");

        tile[18] = new Tile();
        tile[18].image = io.readImage("/tile//tiles/tile_1_1.png");

        tile[19] = new Tile();
        tile[19].image = io.readImage("/tile//tiles/tile_3_1.png");

        tile[20] = new Tile();
        tile[20].image = io.readImage("/tile//tiles/tile_3_2.png");

        tile[21] = new Tile();
        tile[21].image = io.readImage("/tile//tiles/tile_3_3.png");

        tile[22] = new Tile();
        tile[22].image = io.readImage("/tile//tiles/tile_9.png");
        tile[22].collision = true;

        tile[23] = new Tile();
        tile[23].image = io.readImage("/tile//tiles/tile_10_3.png");
        tile[23].collision = true;

        tile[24] = new Tile();
        tile[24].image = io.readImage("/tile//tiles/tile_13_1.png");

        tile[25] = new Tile();
        tile[25].image = io.readImage("/tile//tiles/tile_16_1.png");
        tile[25].collision = true;

        tile[26] = new Tile();
        tile[26].image = io.readImage("/tile//tiles/tile_28_7.png");

        tile[27] = new Tile();
        tile[27].image = io.readImage("/tile//tiles/tile_30.png");

        tile[28] = new Tile();
        tile[28].image = io.readImage("/tile//tiles/tile_31.png");

                tile[29] = new Tile();
                tile[29].image = ImageIO.read(getClass().getResourceAsStream("/tiles/virus.png"));


                tile[30] = new Tile();
                tile[30].image = ImageIO.read(getClass().getResourceAsStream("/tiles/portal.jpg"));

                tile[31] = new Tile();
                tile[31].image = io.readImage("/tile/tiles/fire2-1.png");

                tile[34] = new Tile();
                tile[34].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/blueMapTiles/103.png"));
                tile[34].collision = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (mapName.equals("/util/maps/BlueMap")){

            ArrayList solidBlocks = new ArrayList<>();
            solidBlocks.addAll(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40));
            solidBlocks.addAll(Arrays.asList(47,48,49,50,57,58,87,88,100,101,102,103,104,105,106,110,111,112,113,114,115,116,120,121,122,123,124,125,126));

            for(int i = 0; i < 130; i++) {


                if (!solidBlocks.contains(i)) {
                    try {
                        tile[i] = new Tile();
                        tile[i].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/blueMapTiles/" + i + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }   else if (solidBlocks.contains(i)){
                    try {
                        tile[i] = new Tile();
                        tile[i].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/blueMapTiles/" + i + ".png"));
                        tile[i].collision = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                tile[131] = new Tile();
                tile[131].image = ImageIO.read(getClass().getResourceAsStream("/tiles/portal.jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


        public void loadMap(String filePath) {
            try {
                InputStream is = getClass().getResourceAsStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line;
                int row = 0;

                while (row < gp.maxWorldRow && (line = br.readLine()) != null) {
                    String[] numbers = line.trim().split("\\s+");

                    for (int col = 0; col < gp.maxWorldCol; col++) {
                        mapTileNum[row][col] = Integer.parseInt(numbers[col]);
                    }
                    row++;
                }

                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void changeMap(String mapName, String mapMusic) {

            SoundSystem.stop();
            this.mapName = mapName;
            this.mapMusic = mapMusic;

            getTileImage();
            loadMap(mapName);

            SoundSystem.play(mapMusic);
            gp.setupLevel(mapName);

    }


    public void draw(Graphics2D g2) {

        for (int row = 0; row < gp.maxWorldRow; row++) {
            for (int col = 0; col < gp.maxWorldCol; col++) {

                int tileNum = mapTileNum[row][col];

                int worldX = col * gp.tileSize;
                int worldY = row * gp.tileSize;

                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2.drawImage(tile[tileNum].image, screenX, screenY + 28, gp.tileSize, gp.tileSize, null); //Den tager sig af vores player fødder. Det giver mening at hoved godt kan være over portalen men fødderne kan ikke stå direkte ovenpå
            }
        }
    }
}

