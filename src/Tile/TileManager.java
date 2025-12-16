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

    public class TileManager {

        GamePanel gp;
        FileIO io;
        KeyHandler keyH;
        public Tile[] tile;
        public int mapTileNum[][];


    public String mapName = "/tile/maps/levelOne.txt";
    public String mapMusic = "Resources/musicFiles/mainTheme.wav";

        public TileManager(GamePanel gp, FileIO io){
            this.gp = gp;
            this.io = io;

        tile = new Tile[32];
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];   // RIGTIG ORIENTERING

        getTileImage();
        loadMap(mapName);
    }

    public void getTileImage() {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/floor.png"));
            tile[0].image = io.readImage("/tiles/floor.png");

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/wall.png"));
            tile[1].image = io.readImage("/tiles/wall.png");
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/water.png"));
            tile[2].image = io.readImage("/tiles/water.png");
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/grass.png"));
            tile[3].image = io.readImage("/tiles/grass.png");

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tree.png"));
            tile[4].image = io.readImage("/tiles/tree.png");
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/road.png"));
            tile[5].image = io.readImage("/tiles/road.png");

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/lavaGulv.png"));
            tile[6].image = io.readImage("/tiles/lavaGulv.png");

                tile[7] = new Tile();
                tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_49.png"));
                tile[7].image = io.readImage("/tiles/tile_49.png");

                tile[8] = new Tile();
                tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_34.png"));
                tile[8].image = io.readImage("/tiles/tile_34.png");
                tile[8].collision = true;

                tile[9] = new Tile();
                tile[9].image = io.readImage("/tiles/tile_0_0.png");
                tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_0_0.png"));
                tile[9].collision = true;

                tile[10] = new Tile();
                tile[10].image = io.readImage("/tiles/tile_0_1.png");
                tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_0_1.png"));

                tile[11] = new Tile();
                tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_2_2.png"));
                tile[11].image = io.readImage("/tiles/tile_2_2.png");

                tile[12] = new Tile();
                tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_2_0.png"));
                tile[12].image = io.readImage("/tiles/tile_2_0.png");

                tile[13] = new Tile();
                tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_2_1.png"));
                tile[13].image = io.readImage("/tiles/tile_2_1.png");

                tile[14] = new Tile();
                tile[14].image = io.readImage("/tiles/tile_3_0.png");
                tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_3_0.png"));

                tile[15] = new Tile();
                tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_2_3.png"));
                tile[15].image = io.readImage("/tiles/tile_2_3.png");

                tile[16] = new Tile();
                tile[16].image = io.readImage("/tiles/tile_0_2.png");
                tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_0_2.png"));

                tile[17] = new Tile();
                tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_0_3.png"));
                tile[17].image = io.readImage("/tiles/tile_0_3.png");

                tile[18] = new Tile();
                tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_1_1.png"));
                tile[18].image = io.readImage("/tiles/tile_1_1.png");

                tile[19] = new Tile();
                tile[19].image = io.readImage("/tiles/tile_3_1.png");
                tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_3_1.png"));

                tile[20] = new Tile();
                tile[20].image = io.readImage("/tiles/tile_3_2.png");
                tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_3_2.png"));

                tile[21] = new Tile();
                tile[21].image = io.readImage("/tiles/tile_3_3.png");
                tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_3_3.png"));

                tile[22] = new Tile();
                tile[22].image = io.readImage("/tiles/tile_9.png");
                tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_9.png"));
                tile[22].collision = true;

                tile[23] = new Tile();
                tile[23].image = io.readImage("/tiles/tile_10_3.png");
                tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_10_3.png"));
                tile[23].collision = true;

                tile[24] = new Tile();
                tile[24].image = io.readImage("/tiles/tile_13_1.png");
                tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_13_1.png"));

                tile[25] = new Tile();
                tile[25].image = io.readImage("/tiles/tile_16_1.png");
                tile[25].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_16_1.png"));
                tile[25].collision = true;

                tile[26] = new Tile();
                tile[26].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_28_7.png"));
                tile[26].image = io.readImage("/tiles/tile_28_7.png");

                tile[27] = new Tile();
                tile[27].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_30.png"));
                tile[27].image = io.readImage("/tiles/tile_30.png");

                tile[28] = new Tile();
                tile[28].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/tile_31.png"));
                tile[28].image = io.readImage("/tiles/tile_31.png");

                tile[29] = new Tile();
                tile[29].image = io.readImage("/tiles/water.png");
                tile[29].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/water.png"));


                tile[30] = new Tile();
                tile[30].image = io.readImage("/tiles/portal.jpg");
                tile[30].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/portal.jpg"));

                tile[31] = new Tile();
                tile[31].image = ImageIO.read(getClass().getResourceAsStream("/tile/tiles/fire2-1.png"));

    }


    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;

            while (row < gp.maxWorldRow) {
                String line = br.readLine();
                String[] numbers = line.split(" ");

                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;     // RIGTIG ORIENTERING
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

