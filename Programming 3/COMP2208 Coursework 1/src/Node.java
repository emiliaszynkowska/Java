import java.util.ArrayList;

public class Node implements Comparable<Node> {

    String value;
    Double distance;
    Node parent;
    ArrayList<String> stringChildren;
    ArrayList<Node> children;
    String grid;

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Double getDistance() {
        return distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }
    public Node getParent() {
        return parent;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public ArrayList<String> getStringChildren() {
        return stringChildren;
    }
    public ArrayList<Node> getChildren() {
        return children;
    }
    public String getGrid() {
        return this.grid;
    }
    public void setGrid(String grid) {
        this.grid = grid;
    }

    public Node(String value, Node parent) {
        this.value = value;
        this.parent = parent;
        stringChildren = new ArrayList<String>();
        children = new ArrayList<Node>();
    }

    @Override
    public int compareTo(Node node) {
        if (this.getDistance() < node.getDistance()) {
            return -1;
        }
        else {
            return 1;
        }
    }

}