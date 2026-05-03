import java.util.Random;

public class Main {
    public static void main(String[] args) {
        testHashTable();
        testBST();
    }

    private static void testHashTable() {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(1000000);
            MyTestingClass key = new MyTestingClass(id, "key" + i);
            Student student = new Student(id, "Student" + i, 18);
            table.put(key, student);
        }

        System.out.println("HashTable size: " + table.size());
        for (int i = 0; i < table.getBucketCount(); i++) {
            System.out.println("Bucket " + i + ": " + table.getBucketSize(i));
        }
    }

    private static void testBST() {
        BST<Integer, String> tree = new BST<>();

        tree.put(5, "five");
        tree.put(2, "two");
        tree.put(8, "eight");
        tree.put(1, "one");
        tree.put(3, "three");
        tree.put(7, "seven");

        System.out.println("\nBST size: " + tree.size());
        System.out.println("Value for key 3: " + tree.get(3));

        tree.delete(2);
        System.out.println("BST after delete key 2:");

        for (BST<Integer, String>.Node elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
