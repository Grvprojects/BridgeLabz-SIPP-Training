class Process {
    int id, burst, remaining, priority, waiting, turnaround;
    Process next;

    Process(int id, int burst, int priority) {
        this.id = id;
        this.burst = burst;
        this.remaining = burst;
        this.priority = priority;
    }
}

class RoundRobin {
    Process head;

    void addProcess(int id, int burst, int priority) {
        Process p = new Process(id, burst, priority);
        if (head == null) {
            head = p;
            p.next = head;
            return;
        }
        Process temp = head;
        while (temp.next != head) temp = temp.next;
        temp.next = p;
        p.next = head;
    }

    void removeProcess(int id) {
        if (head == null) return;
        if (head.id == id && head.next == head) {
            head = null;
            return;
        }
        Process temp = head;
        if (head.id == id) {
            while (temp.next != head) temp = temp.next;
            temp.next = head.next;
            head = head.next;
            return;
        }
        while (temp.next != head && temp.next.id != id) temp = temp.next;
        if (temp.next.id == id) temp.next = temp.next.next;
    }

    void simulate(int quantum) {
        if (head == null) return;
        Process temp = head;
        int time = 0;
        while (true) {
            boolean done = true;
            do {
                if (temp.remaining > 0) {
                    done = false;
                    if (temp.remaining > quantum) {
                        time += quantum;
                        temp.remaining -= quantum;
                    } else {
                        time += temp.remaining;
                        temp.waiting = time - temp.burst;
                        temp.turnaround = time;
                        temp.remaining = 0;
                    }
                }
                temp = temp.next;
            } while (temp != head);
            if (done) break;
            displayProcesses();
        }
        displayFinalStats();
    }

    void displayProcesses() {
        if (head == null) return;
        Process temp = head;
        do {
            System.out.println("Process ID: " + temp.id + ", Remaining: " + temp.remaining);
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    void displayFinalStats() {
        if (head == null) return;
        int totalWait = 0, totalTurnaround = 0, count = 0;
        Process temp = head;
        do {
            System.out.println("ID: " + temp.id + ", Waiting Time: " + temp.waiting + ", Turnaround Time: " + temp.turnaround);
            totalWait += temp.waiting;
            totalTurnaround += temp.turnaround;
            count++;
            temp = temp.next;
        } while (temp != head);
        System.out.println("Average Waiting Time: " + (double) totalWait / count);
        System.out.println("Average Turnaround Time: " + (double) totalTurnaround / count);
    }
}

public class RoundRobinScheduler {
    public static void main(String[] args) {
        RoundRobin rr = new RoundRobin();
        rr.addProcess(1, 10, 1);
        rr.addProcess(2, 5, 2);
        rr.addProcess(3, 8, 1);

        int quantum = 4;
        rr.simulate(quantum);
    }
}