import java.util.ArrayList;

public class MusicStore {
    private static final String[] songCatalog = new String[]{"Bohemian Rhapsody", "Hotel California", "Imagine", "Billie Jean", "Smells Like Teen Spirit", "Stairway to Heaven", "Hey Jude", "Thriller", "Sweet Child o' Mine", "Like a Rolling Stone", "Yesterday", "Wonderwall", "Purple Haze", "Hurt", "Baba O'Riley", "I Will Always Love You", "Boogie Wonderland", "Uptown Funk", "Girls Just Want to Have Fun", "Shape of You", "Smile", "Crazy", "Wish You Were Here", "Stayin' Alive", "Under Pressure", "Unchained Melody", "Every Breath You Take", "Don't Stop Believin'", "Piano Man", "Boys Don't Cry", "Wonderful Tonight", "Sweet Caroline", "All of Me", "Rolling in the Deep", "Livin' on a Prayer", "Shape of My Heart", "Dancing Queen", "Careless Whisper", "I Wanna Dance with Somebody", "Sweet Dreams (Are Made of This)", "What a Wonderful World", "I Heard It Through the Grapevine", "Don't You (Forget About Me)", "Eye of the Tiger", "Every Little Thing She Does Is Magic", "Superstition", "I Want to Hold Your Hand", "My Way", "Mrs. Robinson", "Another Brick in the Wall, Part 2"};

    public static void main(String[] args){
        ArrayList<MusicPlayer> musicPlayers = new ArrayList<>();

        musicPlayers.add(new MusicPlayer("Vinyl"));
        musicPlayers.add(new MusicPlayer("iPod"));
        musicPlayers.add(new MusicPlayer("Phone"));
        musicPlayers.add(new MusicPlayer("Walkman"));

        for (MusicPlayer player : musicPlayers){
            player.displayInfo();
        }

        for (MusicPlayer player : musicPlayers){
            String randomSong = songCatalog[(int) (Math.random() * songCatalog.length)];
            int randomVolumeChange = (int) (Math.random() * 100) - 50;
            player.playSong(randomSong);
            player.adjustVolume(randomVolumeChange);
        }

        for (MusicPlayer player : musicPlayers){
            player.displayInfo();
        }
    }
}
