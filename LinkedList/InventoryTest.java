class Item {
    String name;
    int id;
    int quantity;
    double price;
    Item next;

    Item(String name, int id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }
}

class Inventory {
    Item head;

    void addAtBeginning(String name, int id, int quantity, double price) {
        Item i = new Item(name, id, quantity, price);
        i.next = head;
        head = i;
    }

    void addAtEnd(String name, int id, int quantity, double price) {
        Item i = new Item(name, id, quantity, price);
        if (head == null) { head = i; return; }
        Item temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = i;
    }

    void addAtPosition(int pos, String name, int id, int quantity, double price) {
        if (pos <= 0 || head == null) {
            addAtBeginning(name, id, quantity, price);
            return;
        }
        Item temp = head;
        for (int i = 1; i < pos && temp.next != null; i++) temp = temp.next;
        Item i = new Item(name, id, quantity, price);
        i.next = temp.next;
        temp.next = i;
    }

    void removeById(int id) {
        if (head == null) return;
        if (head.id == id) { head = head.next; return; }
        Item temp = head;
        while (temp.next != null && temp.next.id != id) temp = temp.next;
        if (temp.next != null) temp.next = temp.next.next;
    }

    void updateQuantity(int id, int newQty) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) { temp.quantity = newQty; return; }
            temp = temp.next;
        }
    }

    void search(String key) {
        Item temp = head;
        while (temp != null) {
            if (String.valueOf(temp.id).equals(key) || temp.name.equals(key))
                System.out.println(temp.name + " " + temp.id + " " + temp.quantity + " " + temp.price);
            temp = temp.next;
        }
    }

    void displayTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: " + total);
    }

    Item mergeSort(Item node, String by, boolean asc) {
        if (node == null || node.next == null) return node;
        Item middle = getMiddle(node);
        Item nextOfMiddle = middle.next;
        middle.next = null;
        Item left = mergeSort(node, by, asc);
        Item right = mergeSort(nextOfMiddle, by, asc);
        return merge(left, right, by, asc);
    }

    Item merge(Item a, Item b, String by, boolean asc) {
        if (a == null) return b;
        if (b == null) return a;
        Item result;
        boolean condition = by.equals("name") ? (asc ? a.name.compareTo(b.name) <= 0 : a.name.compareTo(b.name) > 0)
                          : (asc ? a.price <= b.price : a.price > b.price);
        if (condition) {
            result = a;
            result.next = merge(a.next, b, by, asc);
        } else {
            result = b;
            result.next = merge(a, b.next, by, asc);
        }
        return result;
    }

    Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    void sortInventory(String by, boolean asc) {
        head = mergeSort(head, by, asc);
    }

    void displayInventory() {
        Item temp = head;
        while (temp != null) {
            System.out.println(temp.name + " " + temp.id + " " + temp.quantity + " " + temp.price);
            temp = temp.next;
        }
    }
}

public class InventoryTest {
    public static void main(String[] args) {
        Inventory inv = new Inventory();

        inv.addAtEnd("Pen", 101, 50, 10);
        inv.addAtBeginning("Notebook", 102, 30, 50);
        inv.addAtPosition(1, "Pencil", 103, 70, 5);

        System.out.println("Inventory:");
        inv.displayInventory();

        System.out.println("\nUpdate Quantity of ID 101 to 100:");
        inv.updateQuantity(101, 100);
        inv.displayInventory();

        System.out.println("\nSearch for item 'Pencil':");
        inv.search("Pencil");

        System.out.println("\nTotal Value:");
        inv.displayTotalValue();

        System.out.println("\nSort by Name Asc:");
        inv.sortInventory("name", true);
        inv.displayInventory();

        System.out.println("\nSort by Price Desc:");
        inv.sortInventory("price", false);
        inv.displayInventory();
    }
}
