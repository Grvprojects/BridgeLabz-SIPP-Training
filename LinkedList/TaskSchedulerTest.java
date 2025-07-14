class Task {
    int id;
    String name;
    int priority;
    String due;
    Task next;

    Task(int id, String name, int priority, String due) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.due = due;
    }
}

class TaskScheduler {
    Task head = null;
    Task current = null;

    void addAtBeginning(int id, String name, int priority, String due) {
        Task t = new Task(id, name, priority, due);
        if (head == null) {
            head = t;
            t.next = head;
            current = head;
            return;
        }
        Task temp = head;
        while (temp.next != head) temp = temp.next;
        t.next = head;
        temp.next = t;
        head = t;
    }

    void addAtEnd(int id, String name, int priority, String due) {
        Task t = new Task(id, name, priority, due);
        if (head == null) {
            head = t;
            t.next = head;
            current = head;
            return;
        }
        Task temp = head;
        while (temp.next != head) temp = temp.next;
        temp.next = t;
        t.next = head;
    }

    void addAtPosition(int pos, int id, String name, int priority, String due) {
        if (pos <= 0 || head == null) {
            addAtBeginning(id, name, priority, due);
            return;
        }
        Task temp = head;
        for (int i = 1; i < pos && temp.next != head; i++) temp = temp.next;
        Task t = new Task(id, name, priority, due);
        t.next = temp.next;
        temp.next = t;
    }

    void removeById(int id) {
        if (head == null) return;
        if (head.id == id && head.next == head) {
            head = null;
            current = null;
            return;
        }
        Task temp = head;
        if (head.id == id) {
            while (temp.next != head) temp = temp.next;
            temp.next = head.next;
            if (current == head) current = head.next;
            head = head.next;
            return;
        }
        while (temp.next != head && temp.next.id != id) temp = temp.next;
        if (temp.next.id == id) {
            if (current == temp.next) current = current.next;
            temp.next = temp.next.next;
        }
    }

    void viewCurrentAndMoveNext() {
        if (current == null) return;
        System.out.println(current.id + " " + current.name + " " + current.priority + " " + current.due);
        current = current.next;
    }

    void displayAllTasks() {
        if (head == null) return;
        Task temp = head;
        do {
            System.out.println(temp.id + " " + temp.name + " " + temp.priority + " " + temp.due);
            temp = temp.next;
        } while (temp != head);
    }

    void searchByPriority(int p) {
        if (head == null) return;
        Task temp = head;
        do {
            if (temp.priority == p)
                System.out.println(temp.id + " " + temp.name + " " + temp.due);
            temp = temp.next;
        } while (temp != head);
    }
}

public class TaskSchedulerTest {
    public static void main(String[] args) {
        TaskScheduler ts = new TaskScheduler();

        ts.addAtEnd(1, "Task A", 1, "2025-07-01");
        ts.addAtBeginning(2, "Task B", 2, "2025-07-02");
        ts.addAtPosition(1, 3, "Task C", 3, "2025-07-03");

        System.out.println("All Tasks:");
        ts.displayAllTasks();

        System.out.println("\nCurrent Task and Move Next:");
        ts.viewCurrentAndMoveNext();
        ts.viewCurrentAndMoveNext();

        System.out.println("\nSearch by Priority 2:");
        ts.searchByPriority(2);

        System.out.println("\nRemove Task with ID 1:");
        ts.removeById(1);
        ts.displayAllTasks();
    }
}