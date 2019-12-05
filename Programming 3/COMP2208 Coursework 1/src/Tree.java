import java.util.ArrayList;
import java.util.Collections;

public class Tree {

    Node root;

    public Node getRoot() {
        return root;
    }
    public void setRoot(Node root) {
        this.root = root;
    }

    public Tree() {
        root = new Node("start", null);
    }

    public Node addNode(Node current, String value) {
        Node newNode = new Node(value, current);
        current.stringChildren.add(value);
        current.children.add(newNode);
        return newNode;
    }

    public ArrayList<String> findPosition(Node target) {
        ArrayList<String> path = new ArrayList<>();
        path.add(target.value);
        Node current = target;
        while (current.getParent() != null) {
            path.add(current.getParent().value);
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }

}
