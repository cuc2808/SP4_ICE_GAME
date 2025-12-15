package util;

import Main.GamePanel;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileIO {
    GamePanel gp;
    public FileIO(GamePanel gp) {
        this.gp = gp;
    }
    public void readWAVFile(String location) {
        byte[] buffer = new byte[4096];

        try {
            File file = new File(location);
            AudioInputStream originalStream = AudioSystem.getAudioInputStream(file);
            AudioFormat originalFormat = originalStream.getFormat();

            AudioFormat targetFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    originalFormat.getSampleRate(),
                    16,
                    originalFormat.getChannels(),
                    originalFormat.getChannels() * 2,
                    originalFormat.getSampleRate(),
                    false
            );

            AudioInputStream convertedStream =
                    AudioSystem.getAudioInputStream(targetFormat, originalStream);

            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(
                    new DataLine.Info(SourceDataLine.class, targetFormat)
            );

            line.open(targetFormat);
            line.start();

            int bytesRead;
            while ((bytesRead = convertedStream.read(buffer)) != -1) {

                // VIGTIGT: STOP HER
                if (Thread.currentThread().isInterrupted()) {
                    line.stop();
                    line.flush();
                    line.close();
                    convertedStream.close();
                    originalStream.close();
                    return;
                }

                line.write(buffer, 0, bytesRead);
            }

            line.drain();
            line.stop();
            line.close();
            convertedStream.close();
            originalStream.close();

        } catch (Exception e) {
            // Thread blev afbrudt
        }
    }


    public BufferedImage readImage(String location) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(location));
        } catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    public int[][] readLocationMapFile(String location) {
        int[][] mapTileNum = new int[gp.maxScreenCollum][gp.maxScreenRow];
          try {
               InputStream is = getClass().getResourceAsStream(location);
               BufferedReader br = new BufferedReader(new InputStreamReader(is));

               int col = 0;
               int row = 0;
               while (col < gp.maxScreenCollum && row < gp.maxScreenRow) {
                  String line = br.readLine();

                   while (col < gp.maxScreenCollum) {
                      String numbers[] = line.split(" ");

                        int num = Integer.parseInt(numbers[col]);

                        mapTileNum[col][row] = num;
                        col++;
                    }
                    if (col == gp.maxScreenCollum) {
                        col = 0;
                        row++;
                    }
                }
                br.close();
            } catch (Exception e) {

          }
        return mapTileNum;
    }


}
