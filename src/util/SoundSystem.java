package util;

import javax.sound.sampled.*;
import java.io.File;

public class SoundSystem {

    private static Thread audioThread;
    private static volatile SourceDataLine line;

    /**
     * Starter et nyt track og stopper automatisk det gamle
     */
    public static synchronized void play(String location) {
        stop();

        audioThread = new Thread(() -> playInternal(location));
        audioThread.setDaemon(true);
        audioThread.start();
    }

    /**
     * Stopper al musik øjeblikkeligt
     */
    public static synchronized void stop() {
        if (line != null) {
            line.stop();
            line.flush();
            line.close();
            line = null;
        }

        if (audioThread != null) {
            audioThread.interrupt();
            audioThread = null;
        }
    }

    // ---- INTERN AFSPILNING ----
    private static void playInternal(String location) {
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

            AudioInputStream stream =
                    AudioSystem.getAudioInputStream(targetFormat, originalStream);

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, targetFormat);
            line = (SourceDataLine) AudioSystem.getLine(info);

            line.open(targetFormat);
            line.start();

            int bytesRead;
            while (!Thread.currentThread().isInterrupted()
                    && (bytesRead = stream.read(buffer)) != -1) {

                line.write(buffer, 0, bytesRead);
            }

            // cleanup hvis sangen slutter naturligt
            stop();

            stream.close();
            originalStream.close();

        } catch (Exception ignored) {
            stop(); // sikker oprydning
        }
    }
}
