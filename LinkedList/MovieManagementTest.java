class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;

    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }
}

class MovieList {
    Movie head, tail;

    void addAtBeginning(String title, String director, int year, double rating) {
        Movie m = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = m;
        } else {
            m.next = head;
            head.prev = m;
            head = m;
        }
    }

    void addAtEnd(String title, String director, int year, double rating) {
        Movie m = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = m;
        } else {
            tail.next = m;
            m.prev = tail;
            tail = m;
        }
    }

    void addAtPosition(int pos, String title, String director, int year, double rating) {
        if (pos <= 0 || head == null) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie temp = head;
        for (int i = 1; i < pos && temp.next != null; i++) temp = temp.next;
        Movie m = new Movie(title, director, year, rating);
        m.next = temp.next;
        m.prev = temp;
        if (temp.next != null) temp.next.prev = m;
        temp.next = m;
        if (m.next == null) tail = m;
    }

    void removeByTitle(String title) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equals(title)) {
                if (temp == head) head = temp.next;
                if (temp == tail) tail = temp.prev;
                if (temp.prev != null) temp.prev.next = temp.next;
                if (temp.next != null) temp.next.prev = temp.prev;
                return;
            }
            temp = temp.next;
        }
    }

    void searchByDirector(String director) {
        Movie temp = head;
        while (temp != null) {
            if (temp.director.equals(director))
                System.out.println(temp.title + " " + temp.director + " " + temp.year + " " + temp.rating);
            temp = temp.next;
        }
    }

    void searchByRating(double rating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.rating == rating)
                System.out.println(temp.title + " " + temp.director + " " + temp.year + " " + temp.rating);
            temp = temp.next;
        }
    }

    void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equals(title)) {
                temp.rating = newRating;
                return;
            }
            temp = temp.next;
        }
    }

    void displayForward() {
        Movie temp = head;
        while (temp != null) {
            System.out.println(temp.title + " " + temp.director + " " + temp.year + " " + temp.rating);
            temp = temp.next;
        }
    }

    void displayReverse() {
        Movie temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " " + temp.director + " " + temp.year + " " + temp.rating);
            temp = temp.prev;
        }
    }
}

public class MovieManagementTest {
    public static void main(String[] args) {
        MovieList list = new MovieList();

        list.addAtEnd("Inception", "Nolan", 2010, 8.8);
        list.addAtBeginning("Interstellar", "Nolan", 2014, 8.6);
        list.addAtPosition(1, "Dunkirk", "Nolan", 2017, 7.9);

        System.out.println("Forward Display:");
        list.displayForward();

        System.out.println("\nReverse Display:");
        list.displayReverse();

        System.out.println("\nSearch by Director (Nolan):");
        list.searchByDirector("Nolan");

        System.out.println("\nUpdate Rating of Dunkirk to 8.1:");
        list.updateRating("Dunkirk", 8.1);
        list.displayForward();

        System.out.println("\nRemove 'Inception':");
        list.removeByTitle("Inception");
        list.displayForward();
    }
}