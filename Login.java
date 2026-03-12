import java.util.Scanner;

public class Login {
    // HashTable for storing usernames and passwords (using separate chaining)
    static class HashTable {
        private LinkedList<Entry>[] table;
        private int size;

        static class Entry {
            String username;
            String password;

            Entry(String u, String p) {
                username = u;
                password = p;
            }
        }

        @SuppressWarnings("unchecked")
        HashTable(int capacity) {
            table = new LinkedList[capacity];
            for (int i = 0; i < capacity; i++) {
                table[i] = new LinkedList<>();
            }
            size = 0;
        }

        private int hash(String key) {
            return Math.abs(key.hashCode()) % table.length;
        }

        void put(String username, String password) {
            int index = hash(username);
            for (Entry e : table[index]) {
                if (e.username.equals(username)) {
                    e.password = password;
                    return;
                }
            }
            table[index].add(new Entry(username, password));
            size++;
        }

        String get(String username) {
            int index = hash(username);
            for (Entry e : table[index]) {
                if (e.username.equals(username)) {
                    return e.password;
                }
            }
            return null;
        }

        // Linear Search to find user (alternative to hashing, but hashing is primary)
        boolean linearSearch(String username, String password) {
            for (LinkedList<Entry> list : table) {
                for (Entry e : list) {
                    if (e.username.equals(username) && e.password.equals(password)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    // LinkedList for dynamic data (used here for simplicity, but can be extended)
    static class LinkedList<T> implements Iterable<T> {
        Node<T> head;

        static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
                this.next = null;
            }
        }

        void add(T data) {
            Node<T> newNode = new Node<>(data);
            if (head == null) {
                head = newNode;
            } else {
                Node<T> temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newNode;
            }
        }

        // For iteration
        @Override
        public java.util.Iterator<T> iterator() {
            return new java.util.Iterator<T>() {
                Node<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }
    }

    public static boolean authenticate(HashTable users, Scanner sc) {
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        // Use hashing for fast lookup
        String storedPass = users.get(user);
        if (storedPass != null && storedPass.equals(pass)) {
            System.out.println("Login successful!");
            return true;
        } else {
            // Fallback to linear search if needed (though hashing is efficient)
            if (users.linearSearch(user, pass)) {
                System.out.println("Login successful via search!");
                return true;
            }
            System.out.println("Invalid credentials.");
            return false;
        }
    }
}