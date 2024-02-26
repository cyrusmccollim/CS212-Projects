public class MusicPlayer {
    private final String brand;
    private String currSong;
    private int volume;

    public MusicPlayer(String brand){
        this.brand = brand;
        this.currSong = "[None]";
        this.volume = 50;
    }

    public void playSong(String songName){
        this.currSong = songName;
        System.out.println("Now playing \"" + songName + "\" on " + brand + "!");
    }

    public void adjustVolume(int volumeChange){
        int newVolume = volume + volumeChange;
        volume += (newVolume >= 0 && newVolume <= 100)? // New volume within 0 - 100 range?
                  volumeChange : (newVolume < 0)? // True: Make adjustment. | False: New volume negative?
                                 (-1) * volume : (100 - volume); // True: Volume becomes 0. | False: Volume becomes 100.
        System.out.println("Volume set to " + volume + " on " + brand + "!");
    }

    public void displayInfo(){
        System.out.println("Brand: " + brand + " | Current Song: \"" + currSong + "\" | Volume: " + volume);
    }
}
