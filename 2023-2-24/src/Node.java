public class Node {

    private volatile Thread thread;
    private volatile String mode;
    private volatile Node pre;
    private volatile Node next;

    private static final String MODE_SHARED = "SHARED";
    private static final String MODE_EXCLUSION = "EXCLUSION";

    public Node(Thread thread, String mode) {
        this.thread = thread;
        this.mode = mode;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
