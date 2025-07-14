class Student {
    int roll;
    String name;
    int age;
    String grade;
    Student next;

    Student(int roll, String name, int age, String grade) {
        this.roll = roll;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
}

class StudentList {
    Student head;

    void addAtBeginning(int roll, String name, int age, String grade) {
        Student s = new Student(roll, name, age, grade);
        s.next = head;
        head = s;
    }

    void addAtEnd(int roll, String name, int age, String grade) {
        Student s = new Student(roll, name, age, grade);
        if (head == null) { head = s; return; }
        Student temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = s;
    }

    void addAtPosition(int pos, int roll, String name, int age, String grade) {
        if (pos <= 0 || head == null) {
            addAtBeginning(roll, name, age, grade);
            return;
        }
        Student temp = head;
        for (int i = 1; i < pos && temp.next != null; i++) temp = temp.next;
        Student s = new Student(roll, name, age, grade);
        s.next = temp.next;
        temp.next = s;
    }

    void deleteByRoll(int roll) {
        if (head == null) return;
        if (head.roll == roll) { head = head.next; return; }
        Student temp = head;
        while (temp.next != null && temp.next.roll != roll) temp = temp.next;
        if (temp.next != null) temp.next = temp.next.next;
    }

    void searchByRoll(int roll) {
        Student temp = head;
        while (temp != null) {
            if (temp.roll == roll) {
                System.out.println(temp.roll + " " + temp.name + " " + temp.age + " " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found");
    }

    void updateGrade(int roll, String newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.roll == roll) {
                temp.grade = newGrade;
                return;
            }
            temp = temp.next;
        }
    }

    void display() {
        Student temp = head;
        while (temp != null) {
            System.out.println(temp.roll + " " + temp.name + " " + temp.age + " " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentRecordTest {
    public static void main(String[] args) {
        StudentList list = new StudentList();

        list.addAtEnd(1, "Alice", 20, "A");
        list.addAtBeginning(2, "Bob", 21, "B");
        list.addAtPosition(1, 3, "Charlie", 19, "C");

        System.out.println("All Students:");
        list.display();

        System.out.println("\nSearching for Roll 2:");
        list.searchByRoll(2);

        System.out.println("\nUpdating Grade of Roll 3 to A+");
        list.updateGrade(3, "A+");
        list.display();

        System.out.println("\nDeleting Roll 1:");
        list.deleteByRoll(1);
        list.display();
    }
}
