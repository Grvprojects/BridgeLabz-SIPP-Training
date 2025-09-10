import java.util.LinkedList;
import java.util.Queue;

public class MusicPlaylistApp {

    // Base class that can be extended for videos, podcasts — shows Polymorphism
    static class MediaPlayer {
        public void play(String media) {
            System.out.println(" Now playing: " + media);
        }
    }

    // Music player class extending MediaPlayer
    static class MusicPlayer extends MediaPlayer {
        private Queue<String> playlist;

        // Constructor
        public MusicPlayer() {
            playlist = new LinkedList<>();  // Using LinkedList as Queue
        }

        // Add song to the end of playlist
        public void addSong(String song) {
            playlist.add(song);
            System.out.println("Added to playlist: " + song);
        }

        // Remove current song and play next
        public void playNext() {
            if (!playlist.isEmpty()) {
                String nextSong = playlist.poll();  // removes from front
                play(nextSong);  // from MediaPlayer
            } else {
                System.out.println(" Playlist is empty.");
            }
        }

        // Peek current song without removing
        public void currentSong() {
            if (!playlist.isEmpty()) {
                System.out.println(" Current song: " + playlist.peek());
            } else {
                System.out.println(" No song is currently queued.");
            }
        }

        // Remove a specific song
        public void removeSong(String song) {
            if (playlist.remove(song)) {
                System.out.println(" Removed song: " + song);
            } else {
                System.out.println(" Song not found in playlist.");
            }
        }

        // Print entire playlist
        public void showPlaylist() {
            if (playlist.isEmpty()) {
                System.out.println(" Playlist is empty.");
            } else {
                System.out.println(" Playlist: " + playlist);
            }
        }
    }

    // Main method to test it all
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();

        // Add songs
        player.addSong("Kesariya");
        player.addSong("Believer");
        player.addSong("Raataan Lambiyan");

        // Show current song
        player.currentSong();

        // Play next song
        player.playNext();

        // Show current song
        player.currentSong();

        // Remove specific song
        player.removeSong("Believer");

        // Show playlist
        player.showPlaylist();

        // Play remaining songs
        player.playNext();
        player.playNext();

        // Try playing again
        player.playNext();
    }
}
