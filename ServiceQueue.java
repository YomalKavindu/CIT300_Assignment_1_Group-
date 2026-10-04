public class ServiceQueue {

    // Helper class for service requests
    public static class ServiceRequest {
        private String requestId;
        private String studentId;
        private String requestType;

        public ServiceRequest(String requestId, String studentId, String requestType) {
            this.requestId = requestId;
            this.studentId = studentId;
            this.requestType = requestType;
        }

        public String getRequestId() {
            return requestId;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getRequestType() {
            return requestType;
        }

        @Override
        public String toString() {
            return String.format("Request ID: %-8s | Student ID: %-10s | Service: %s", 
                    requestId, studentId, requestType);
        }
    }

    // Node class representing each element in the queue
    private static class Node {
        ServiceRequest data;
        Node next;

        Node(ServiceRequest data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public ServiceQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Add a new service request to the back of the queue (Enqueue)
    public void enqueue(ServiceRequest request) {
        Node newNode = new Node(request);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // Process and remove the next service request at the front of the queue (Dequeue)
    public ServiceRequest dequeue() {
        if (isEmpty()) {
            return null;
        }
        ServiceRequest removedRequest = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return removedRequest;
    }

    // Peek at the request at the front of the queue
    public ServiceRequest peek() {
        if (isEmpty()) {
            return null;
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }

    // Display all pending service requests in the queue
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No pending service requests in the queue.");
            return;
        }

        System.out.println("\n***************************************************************************");
        System.out.println("                       PENDING SERVICE REQUESTS (QUEUE)                      ");
        System.out.println("*****************************************************************************");
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.printf("[%d] %s\n", position++, current.data);
            current = current.next;
        }
        System.out.println("*****************************************************************************");
        System.out.println("Total Pending Requests: " + size);
    }
}