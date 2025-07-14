import java.util.*;

class User {
    int id, age;
    String name;
    List<Integer> friends;
    User next;

    User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.friends = new ArrayList<>();
    }
}

class SocialMedia {
    User head;

    void addUser(int id, String name, int age) {
        User u = new User(id, name, age);
        u.next = head;
        head = u;
    }

    User findUserById(int id) {
        User temp = head;
        while (temp != null) {
            if (temp.id == id) return temp;
            temp = temp.next;
        }
        return null;
    }

    void addFriend(int id1, int id2) {
        User u1 = findUserById(id1);
        User u2 = findUserById(id2);
        if (u1 != null && u2 != null && !u1.friends.contains(id2)) {
            u1.friends.add(id2);
            u2.friends.add(id1);
        }
    }

    void removeFriend(int id1, int id2) {
        User u1 = findUserById(id1);
        User u2 = findUserById(id2);
        if (u1 != null && u2 != null) {
            u1.friends.remove(Integer.valueOf(id2));
            u2.friends.remove(Integer.valueOf(id1));
        }
    }

    void mutualFriends(int id1, int id2) {
        User u1 = findUserById(id1);
        User u2 = findUserById(id2);
        if (u1 != null && u2 != null) {
            System.out.print("Mutual Friends: ");
            for (int fid : u1.friends) {
                if (u2.friends.contains(fid)) System.out.print(fid + " ");
            }
            System.out.println();
        }
    }

    void displayFriends(int id) {
        User u = findUserById(id);
        if (u != null) {
            System.out.print("Friends of " + u.name + " (ID: " + u.id + "): ");
            for (int fid : u.friends) System.out.print(fid + " ");
            System.out.println();
        }
    }

    void searchUser(String key) {
        User temp = head;
        while (temp != null) {
            if (String.valueOf(temp.id).equals(key) || temp.name.equalsIgnoreCase(key))
                System.out.println("Found: ID=" + temp.id + ", Name=" + temp.name);
            temp = temp.next;
        }
    }

    void countFriends() {
        User temp = head;
        while (temp != null) {
            System.out.println("User ID: " + temp.id + ", Friends Count: " + temp.friends.size());
            temp = temp.next;
        }
    }
}

public class SocialMediaConnections {
    public static void main(String[] args) {
        SocialMedia sm = new SocialMedia();
        sm.addUser(1, "Alice", 21);
        sm.addUser(2, "Bob", 22);
        sm.addUser(3, "Charlie", 20);
        sm.addUser(4, "David", 23);

        sm.addFriend(1, 2);
        sm.addFriend(1, 3);
        sm.addFriend(2, 3);

        sm.displayFriends(1);
        sm.displayFriends(2);

        sm.mutualFriends(1, 2);

        sm.removeFriend(1, 2);
        sm.displayFriends(1);
        sm.displayFriends(2);

        sm.searchUser("Alice");
        sm.searchUser("3");

        sm.countFriends();
    }
}