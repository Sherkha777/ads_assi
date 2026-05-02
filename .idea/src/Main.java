import java.util.Random;

public class Main {

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

    public static void main(String[] args) {
        testHashTable();
    }

}