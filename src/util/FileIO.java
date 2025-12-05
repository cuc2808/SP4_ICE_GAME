package util;

import Main.GamePanel;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class FileIO {
    GamePanel gp;
    public FileIO(GamePanel gp) {
        this.gp = gp;
    }

    public byte[] readWAVFile(String location) {
        byte[] buffer = new byte[4096];
        try {
            File file = new File(location);
            AudioInputStream originalStream = AudioSystem.getAudioInputStream(file);
            AudioFormat originalFormat = originalStream.getFormat();

            // Konverter til 16-bit PCM
            AudioFormat targetFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    originalFormat.getSampleRate(),
                    16,
                    originalFormat.getChannels(),
                    originalFormat.getChannels() * 2,
                    originalFormat.getSampleRate(),
                    false
            );

            AudioInputStream convertedStream = AudioSystem.getAudioInputStream(targetFormat, originalStream);

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, targetFormat);

            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(targetFormat);
            line.start();

            buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = convertedStream.read(buffer)) != -1) {
                line.write(buffer, 0, bytesRead);
            }

            line.drain();
            line.stop();
            line.close();
            convertedStream.close();
            originalStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }
    public BufferedImage readImage(String location) {
        BufferedImage Image = null;
        try {
            Image = ImageIO.read(getClass().getResourceAsStream(location));
        } catch (IOException e){
            e.printStackTrace();
        }
        return Image;
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
