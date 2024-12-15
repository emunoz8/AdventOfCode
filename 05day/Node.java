import java.util.ArrayList;

public class Node {
    String value;
    ArrayList<Node> greater;
    ArrayList<Node> smaller;

    public Node(String value) {
        this.value = value;
        this.greater = new ArrayList<>();
        this.smaller = new ArrayList<>();

    }

    public void addLarger(Node parent) {
        greater.add(parent);
    }

    public void addSmaller(Node child) {
        smaller.add(child);
    }
}
