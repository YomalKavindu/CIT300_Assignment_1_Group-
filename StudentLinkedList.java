public class StudentLinkedList {

    // Node class representing each element in the linked list
    private static class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public StudentLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Check if a Student ID already exists in the linked list
    public boolean contains(String studentId) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Add a new student record to the linked list
    public boolean addStudent(Student student) {
        if (student == null || contains(student.getStudentId())) {
            return false; // Duplicate ID or invalid student
        }

        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    // Update an existing student record by ID
    public boolean updateStudent(String studentId, String newName, String newProgramme, double newMarks) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                current.data.setName(newName);
                current.data.setProgramme(newProgramme);
                current.data.setMarks(newMarks);
                return true;
            }
            current = current.next;
        }
        return false; // Student ID not found
    }

    // Delete a student record by ID and return the removed Student object (useful for Stack/Undo)
    public Student deleteStudent(String studentId) {
        if (head == null) {
            return null;
        }

        if (head.data.getStudentId().equalsIgnoreCase(studentId)) {
            Student deletedStudent = head.data;
            head = head.next;
            size--;
            return deletedStudent;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.getStudentId().equalsIgnoreCase(studentId)) {
                Student deletedStudent = current.next.data;
                current.next = current.next.next;
                size--;
                return deletedStudent;
            }
            current = current.next;
        }

        return null; // Student ID not found
    }

    // Search for a student record by ID
    public Student searchStudent(String studentId) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // Display all records stored in the linked list
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found in the linked list.");
            return;
        }

        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.println("                         STUDENT RECORDS (LINKED LIST)                       ");
        System.out.println("-----------------------------------------------------------------------------");
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Total Records: " + size);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }
}