import java.util.EmptyStackException;

public class ActionStack {

    // Node class representing an element in the stack
    private static class Node {
        String actionDescription;
        Student deletedStudent; // Can be null if the action isn't a deletion
        Node next;

        Node(String actionDescription, Student deletedStudent) {
            this.actionDescription = actionDescription;
            this.deletedStudent = deletedStudent;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    public ActionStack() {
        this.top = null;
        this.size = 0;
    }

    // Push an action description onto the stack
    public void push(String actionDescription) {
        push(actionDescription, null);
    }

    // Push an action description along with a deleted student object
    public void push(String actionDescription, Student deletedStudent) {
        Node newNode = new Node(actionDescription, deletedStudent);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop the most recent action off the stack
    public String pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        String action = top.actionDescription;
        top = top.next;
        size--;
        return action;
    }

    // Peek at the most recent action without removing it
    public String peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.actionDescription;
    }

    // Peek at the last deleted student object
    public Student peekDeletedStudent() {
        if (isEmpty()) {
            return null;
        }
        return top.deletedStudent;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }

    // Display history of recent actions stored in the stack
    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("No recent actions found in history.");
            return;
        }

        System.out.println("\n***************************************************************************");
        System.out.println("                         RECENT ACTIONS HISTORY (STACK)                      ");
        System.out.println("*****************************************************************************");
        Node current = top;
        int count = 1;
        while (current != null) {
            System.out.printf("%d. %s\n", count++, current.actionDescription);
            if (current.deletedStudent != null) {
                System.out.println("   --> Deleted Record: " + current.deletedStudent);
            }
            current = current.next;
        }
        System.out.println("******************************************************************************");
    }
}