import java.util.ArrayList;

public class Node implements Comparable<Node> {

    String value;
    Integer distance;
    Node parent;
    Boolean visited;
    ArrayList<String> stringChildren;
    ArrayList<Node> children;
    Integer[][] grid;

    public Node getParent() { return parent; }
    public Integer getDistance() { return distance; }
    public void setDistance(Integer distance) { this.distance = distance; }

    public Node(String value, Node parent) {
        this.value = value;
        this.parent = parent;
        this.visited = false;
        stringChildren = new ArrayList<String>();
        children = new ArrayList<Node>();
    }

    public void setGrid(Integer[][] grid) {
        this.grid = grid;
    }
    public Integer[][] getGrid() {
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