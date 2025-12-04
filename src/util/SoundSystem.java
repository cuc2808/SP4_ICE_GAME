package util;

import javax.sound.sampled.*;
import java.io.File;

public class SoundSystem {

    public static void playTrack(String location) { //Plays the track offSync
        new Thread(() -> {
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

                byte[] buffer = new byte[4096];
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
        }).start();
    }
    public static void musicBreak(String location) { //Plays the track offSync
        new Thread(() -> {
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

                byte[] buffer = new byte[4096];
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
        }).run();
    }
}
