package util;

public class SoundSystem {
    static FileIO io;
    public SoundSystem(FileIO io) {
        this.io = io;
    }

    public static void playTrack(String location) { //Plays the track offSync
        new Thread(() -> { io.readWAVFile(location);
        }).start();
    }
    public static void musicBreak(String location) { //Plays the track offSync
        new Thread(() -> { io.readWAVFile(location);
        }).run();
    }
}
