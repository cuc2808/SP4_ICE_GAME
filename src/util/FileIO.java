package util;

import Main.GamePanel;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
        int row = 0;
        int collum = 0;
        int[][] map = new int[collum][row];
        File file = new File(location);

        while (row < gp.maxScreenRow && collum < gp.maxScreenCollum) {
            try {
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    while (collum < gp.maxScreenCollum) {
                        String data[] = line.split(" ");
                        int dataNum = Integer.parseInt(data[collum]);
                        map[collum][row] = dataNum;
                        collum++;
                    }
                    if (collum == gp.maxScreenCollum) {
                        collum = 0;
                        row++;
                    }
                }
                scan.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }



}
