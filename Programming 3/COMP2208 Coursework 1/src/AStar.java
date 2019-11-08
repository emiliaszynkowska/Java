import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStar extends Search {

    PriorityQueue<Node> nodes;

    public AStar() {
        super();
    }

    public void run() {
        System.out.println("A* Search is running..");
        tree.root.setDistance(tree.findPosition(tree.root).size() + findEuclideanDistance());
        nodes = new PriorityQueue<>();
        nodes.add(tree.root);
        while (!nodes.isEmpty()) {
            solve(nodes.remove());
        }
    }

    public Integer findManhattanDistance() {
        int d1 = Math.abs(game.getPos(4,'x') - game.getPos(1,'x')) + Math.abs(game.getPos(4,'y') - game.getPos(1,'x'));
        int d2 = Math.abs(game.getPos(4,'x') - game.getPos(2,'x')) + Math.abs(game.getPos(4,'y') - game.getPos(2,'x'));
        int d3 = Math.abs(game.getPos(4,'x') - game.getPos(3,'x')) + Math.abs(game.getPos(4,'y') - game.getPos(3,'x'));
        return d1 + d2 + d3;
    }
    public Double findEuclideanDistance() {
        Double d1 = Math.sqrt(Math.pow((game.getPos(4,'x') - game.getPos(1,'x')),2) + Math.pow((game.getPos(4,'y') - game.getPos(1,'x')),2));
        Double d2 = Math.sqrt(Math.pow((game.getPos(4,'x') - game.getPos(2,'x')),2) + Math.pow((game.getPos(4,'y') - game.getPos(2,'x')),2));
        Double d3 = Math.sqrt(Math.pow((game.getPos(4,'x') - game.getPos(3,'x')),2) + Math.pow((game.getPos(4,'y') - game.getPos(3,'x')),2));
        return d1 + d2 + d3;
    }

    public Boolean solve(Node current) {
        checkReachedGoal(current);
        game.set(decodeArray(game, current.getGrid()));
        if (!(current.stringChildren.contains("up")) && (game.up() == true)) {
            Node newNode = tree.addNode(current,"up");
            newNode.setGrid(copyArray(game.grid));
            newNode.setDistance(tree.findPosition(newNode).size() + findEuclideanDistance());
            checkReachedGoal(newNode);
            nodes.add(newNode);
            game.down();
            counter ++;
        }
        if ((!current.stringChildren.contains("down")) && (game.down() == true)) {
            Node newNode = tree.addNode(current,"down");
            newNode.setGrid(copyArray(game.grid));
            newNode.setDistance(tree.findPosition(newNode).size() + findEuclideanDistance());
            checkReachedGoal(newNode);
            nodes.add(newNode);
            game.up();
            counter ++;
        }
        if ((!current.stringChildren.contains("right")) && (game.right() == true)) {
            Node newNode = tree.addNode(current,"right");
            newNode.setGrid(copyArray(game.grid));
            newNode.setDistance(tree.findPosition(newNode).size() + findEuclideanDistance());
            checkReachedGoal(newNode);
            nodes.add(newNode);
            game.left();
            counter ++;
        }
        if ((!current.stringChildren.contains("left")) && (game.left() == true)) {
            Node newNode = tree.addNode(current,"left");
            newNode.setGrid(copyArray(game.grid));
            newNode.setDistance(tree.findPosition(newNode).size() + findEuclideanDistance());
            checkReachedGoal(newNode);
            nodes.add(newNode);
            game.right();
            counter ++;
        }
        return false;
    }

}
