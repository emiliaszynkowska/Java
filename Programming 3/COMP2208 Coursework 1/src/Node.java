import java.util.ArrayList;

public class Node implements Comparable<Node> {

    String value;
    Double distance;
    Node parent;
    ArrayList<String> stringChildren;
    ArrayList<Node> children;
    String grid;

    public Node getParent() { return parent; }
    public Double getDistance() { return distance; }
    public void setDistance(Double distance) { this.distance = distance; }

    public Node(String value, Node parent) {
        this.value = value;
        this.parent = parent;
        stringChildren = new ArrayList<String>();
        children = new ArrayList<Node>();
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }
    public String getGrid() {
        return grid;
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