class Ticket {
    int id;
    String customer, movie;
    int seat;
    String time;
    Ticket next;

    Ticket(int id, String customer, String movie, int seat, String time) {
        this.id = id;
        this.customer = customer;
        this.movie = movie;
        this.seat = seat;
        this.time = time;
    }
}

class TicketSystem {
    Ticket head;

    void addTicket(int id, String customer, String movie, int seat, String time) {
        Ticket t = new Ticket(id, customer, movie, seat, time);
        if (head == null) {
            head = t;
            t.next = t;
            return;
        }
        Ticket temp = head;
        while (temp.next != head) temp = temp.next;
        temp.next = t;
        t.next = head;
    }

    void removeTicket(int id) {
        if (head == null) return;
        if (head.id == id && head.next == head) {
            head = null;
            return;
        }
        Ticket temp = head;
        if (head.id == id) {
            while (temp.next != head) temp = temp.next;
            temp.next = head.next;
            head = head.next;
            return;
        }
        while (temp.next != head && temp.next.id != id) temp = temp.next;
        if (temp.next.id == id) temp.next = temp.next.next;
    }

    void displayTickets() {
        if (head == null) return;
        Ticket temp = head;
        do {
            System.out.println(temp.id + " " + temp.customer + " " + temp.movie + " " + temp.seat + " " + temp.time);
            temp = temp.next;
        } while (temp != head);
    }

    void searchTicket(String key) {
        if (head == null) return;
        Ticket temp = head;
        do {
            if (temp.customer.equalsIgnoreCase(key) || temp.movie.equalsIgnoreCase(key))
                System.out.println("Found: " + temp.id + " " + temp.customer + " " + temp.movie);
            temp = temp.next;
        } while (temp != head);
    }

    void countTickets() {
        if (head == null) {
            System.out.println("Total Booked Tickets: 0");
            return;
        }
        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        System.out.println("Total Booked Tickets: " + count);
    }
}

public class TicketReservationSystem {
    public static void main(String[] args) {
        TicketSystem ts = new TicketSystem();

        ts.addTicket(101, "Alice", "Avengers", 1, "10:00AM");
        ts.addTicket(102, "Bob", "Batman", 2, "12:00PM");
        ts.addTicket(103, "Charlie", "Avengers", 3, "2:00PM");

        ts.displayTickets();
        ts.searchTicket("Avengers");
        ts.removeTicket(102);
        ts.displayTickets();
        ts.countTickets();
    }
}