import java.util.ArrayList;

public class Node {
    private String value;
    private ArrayList<Node> greater;
    private ArrayList<Node> smaller;

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

    public String getValue() {
        return this.value;
    }

    public ArrayList<Node> getLesser() {
        return this.smaller;
    }

    public boolean isLess(Node check) {
        for (Node node : this.getLesser()) {
            if (node == check)
                return false;

        }
        return true;
    }

    public static Node findNode(ArrayList<Node> list, String number) {

        for (Node node : list) {
            if (node.value.equals(number))
                return node;
        }
        return null;
    }

}
