public class StudentHashTable {
    private static class HashNode {
        String key;
        Student value;
        HashNode next;
        HashNode(String key, Student value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    private HashNode[] table;
    private final int capacity;
    private int size;
    public StudentHashTable() {
        this(16); 
    }
    public StudentHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new HashNode[capacity];
        this.size = 0;
    }
    private int hashFunction(String key) {
        int hashCode = Math.abs(key.toLowerCase().hashCode());
        return hashCode % capacity;
    }
    public void put(String key, Student student) {
        int index = hashFunction(key);
        HashNode head = table[index];
        while (head != null) {
            if (head.key.equalsIgnoreCase(key)) {
                head.value = student;
                return;
            }
            head = head.next;
        }
        HashNode newNode = new HashNode(key, student);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
    }
    public Student get(String key) {
        int index = hashFunction(key);
        HashNode head = table[index];
        while (head != null) {
            if (head.key.equalsIgnoreCase(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null; 
    }
    public boolean remove(String key) {
        int index = hashFunction(key);
        HashNode head = table[index];
        HashNode prev = null;
        while (head != null) {
            if (head.key.equalsIgnoreCase(key)) {
                if (prev != null) {
                    prev.next = head.next;
                } else {
                    table[index] = head.next;
                }
                size--;
                return true;
            }
            prev = head;
            head = head.next;
        }
        return false;
    }
    public int getSize() {
        return size;
    }
}