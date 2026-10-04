public class StudentBST {

    private static class Node {
        Student data;
        Node left;
        Node right;
        Node(Student data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    private Node root;
    public StudentBST() {
        this.root = null;
    }
    public void insert(Student student) {
        if (student == null) return;
        root = insertRecursive(root, student);
    }
    private Node insertRecursive(Node current, Student student) {
        if (current == null) {
            return new Node(student);
        }
        int comparison = student.getStudentId().compareToIgnoreCase(current.data.getStudentId());
        if (comparison < 0) {
            current.left = insertRecursive(current.left, student);
        } else if (comparison > 0) {
            current.right = insertRecursive(current.right, student);
        } else {
            current.data = student;
        }
        return current;
    }
    public Student search(String studentId) {
        return searchRecursive(root, studentId);
    }
    private Student searchRecursive(Node current, String studentId) {
        if (current == null) {
            return null;
        }
        int comparison = studentId.compareToIgnoreCase(current.data.getStudentId());
        if (comparison == 0) {
            return current.data;
        }
        return comparison < 0 
                ? searchRecursive(current.left, studentId) 
                : searchRecursive(current.right, studentId);
    }
    public void displayInOrder() {
        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }
        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.println("                    STUDENT RECORDS (BST IN-ORDER TRAVERSAL)                ");
        System.out.println("-----------------------------------------------------------------------------");
        inOrderRecursive(root);
        System.out.println("-----------------------------------------------------------------------------");
    }
    private void inOrderRecursive(Node node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.println(node.data);
            inOrderRecursive(node.right);
        }
    }
}