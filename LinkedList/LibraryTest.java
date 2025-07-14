class Book {
    String title, author, genre, status;
    int id;
    Book next, prev;

    Book(String title, String author, String genre, int id, String status) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.id = id;
        this.status = status;
    }
}

class Library {
    Book head, tail;

    void addAtBeginning(String title, String author, String genre, int id, String status) {
        Book b = new Book(title, author, genre, id, status);
        if (head == null) { head = tail = b; return; }
        b.next = head;
        head.prev = b;
        head = b;
    }

    void addAtEnd(String title, String author, String genre, int id, String status) {
        Book b = new Book(title, author, genre, id, status);
        if (tail == null) { head = tail = b; return; }
        tail.next = b;
        b.prev = tail;
        tail = b;
    }

    void addAtPosition(int pos, String title, String author, String genre, int id, String status) {
        if (pos <= 0 || head == null) {
            addAtBeginning(title, author, genre, id, status);
            return;
        }
        Book temp = head;
        for (int i = 1; i < pos && temp.next != null; i++) temp = temp.next;
        if (temp.next == null) {
            addAtEnd(title, author, genre, id, status);
            return;
        }
        Book b = new Book(title, author, genre, id, status);
        b.next = temp.next;
        b.prev = temp;
        temp.next.prev = b;
        temp.next = b;
    }

    void removeById(int id) {
        Book temp = head;
        while (temp != null && temp.id != id) temp = temp.next;
        if (temp == null) return;
        if (temp == head) head = head.next;
        if (temp == tail) tail = tail.prev;
        if (temp.prev != null) temp.prev.next = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev;
    }

    void search(String key) {
        Book temp = head;
        while (temp != null) {
            if (temp.title.equals(key) || temp.author.equals(key))
                System.out.println(temp.id + " " + temp.title + " " + temp.author + " " + temp.status);
            temp = temp.next;
        }
    }

    void updateStatus(int id, String newStatus) {
        Book temp = head;
        while (temp != null) {
            if (temp.id == id) { temp.status = newStatus; return; }
            temp = temp.next;
        }
    }

    void displayForward() {
        Book temp = head;
        while (temp != null) {
            System.out.println(temp.id + " " + temp.title + " " + temp.author + " " + temp.status);
            temp = temp.next;
        }
    }

    void displayReverse() {
        Book temp = tail;
        while (temp != null) {
            System.out.println(temp.id + " " + temp.title + " " + temp.author + " " + temp.status);
            temp = temp.prev;
        }
    }

    void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total Books: " + count);
    }
}

public class LibraryTest {
    public static void main(String[] args) {
        Library lib = new Library();

        lib.addAtEnd("Book1", "Author1", "Fiction", 1, "Available");
        lib.addAtBeginning("Book2", "Author2", "Drama", 2, "Issued");
        lib.addAtPosition(1, "Book3", "Author3", "History", 3, "Available");

        System.out.println("Books (Forward):");
        lib.displayForward();

        System.out.println("\nBooks (Reverse):");
        lib.displayReverse();

        System.out.println("\nUpdate Status of ID 1:");
        lib.updateStatus(1, "Issued");
        lib.displayForward();

        System.out.println("\nSearch by Title 'Book2':");
        lib.search("Book2");

        System.out.println("\nRemove Book ID 2:");
        lib.removeById(2);
        lib.displayForward();

        System.out.println("\nTotal Books:");
        lib.countBooks();
    }
}
