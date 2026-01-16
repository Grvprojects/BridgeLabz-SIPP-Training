public class BrowserHistoryApp {

    // Node class representing each page in history
    static class HistoryNode {
        String url;
        HistoryNode prev;
        HistoryNode next;

        public HistoryNode(String url) {
            this.url = url;
            this.prev = null;
            this.next = null;
        }
    }

    // Base history class using encapsulation and abstraction
    static class BrowserHistory {
        private HistoryNode current;

        // Visit a new page
        public void visitPage(String url) {
            HistoryNode newNode = new HistoryNode(url);

            if (current != null) {
                current.next = newNode;
                newNode.prev = current;
            }

            current = newNode;
            System.out.println("Visited: " + url);
        }

        // Navigate backward
        public void goBack() {
            if (current != null && current.prev != null) {
                current = current.prev;
                System.out.println("Went back to: " + current.url);
            } else {
                System.out.println("No previous page.");
            }
        }

        // Navigate forward
        public void goForward() {
            if (current != null && current.next != null) {
                current = current.next;
                System.out.println("Went forward to: " + current.url);
            } else {
                System.out.println("No forward page.");
            }
        }

        // Get current page
        public String getCurrentPage() {
            return current != null ? current.url : "No page visited yet.";
        }
    }

    // Child class demonstrating inheritance and polymorphism
    static class MusicPlayerHistory extends BrowserHistory {
        public void playCurrentSong() {
            System.out.println("🎵 Now playing: " + getCurrentPage());
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();
        browser.visitPage("practice.bridgelabz.com");
        browser.visitPage("github.com");
        browser.visitPage("gla.ac.in");
        browser.goBack();
        browser.goBack();
        browser.goForward();
        System.out.println("Current Page: " + browser.getCurrentPage());

        System.out.println("\nMusic Player History (Inheritance Example)");
        MusicPlayerHistory musicPlayer = new MusicPlayerHistory();
        musicPlayer.visitPage("s.mp3");
        musicPlayer.visitPage("s2.mp3");
        musicPlayer.playCurrentSong();
        musicPlayer.goBack();
        musicPlayer.playCurrentSong();
    }
}
