import java.util.Random;

public class Main {

    // Part 1.2
    static void testHashTable() {
        System.out.println("========== Part 1: MyHashTable ==========");

        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(1000);

        String[] firstNames = {"Alice", "Bob", "Carol", "Dave", "Eve",
                "Frank", "Grace", "Hank", "Ivy", "Jack"};
        String[] lastNames  = {"Smith", "Jones", "Brown", "Taylor", "Wilson",
                "Davis", "Clark", "Lewis", "Lee", "Walker"};
        Random rnd = new Random(42);

        for (int i = 0; i < 10_000; i++) {
            String fn  = firstNames[rnd.nextInt(firstNames.length)];
            String ln  = lastNames[rnd.nextInt(lastNames.length)];
            int    age = 18 + rnd.nextInt(50);
            MyTestingClass key   = new MyTestingClass(i, fn + ln + i);
            Student        value = new Student(fn, ln, age);
            table.put(key, value);
        }

        System.out.println("Total elements inserted: " + table.getSize());
        System.out.println("\nElements per bucket:");
        table.printBucketSizes();
    }

    // Part 2.2
    static void testBST() {
        System.out.println("========== Part 2: BST ==========");

        BST<Integer, String> tree = new BST<>();

        tree.put(5, "five");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(1, "one");
        tree.put(4, "four");
        tree.put(6, "six");
        tree.put(8, "eight");
        tree.put(2, "two");

        System.out.println("Size: " + tree.size());

        System.out.println("\nIn-order traversal:");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        tree.delete(3);
        System.out.println("\nAfter deleting key 3, size: " + tree.size());
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        System.out.println("\nget(7) = " + tree.get(7));
        System.out.println("get(3) = " + tree.get(3) + " (deleted, should be null)");
    }

    public static void main(String[] args) {
        testHashTable();
        testBST();
    }

}