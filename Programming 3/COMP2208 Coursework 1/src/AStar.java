import java.util.PriorityQueue;

public class AStar extends Search {

    PriorityQueue<Node> nodes;

    public AStar() {
        super();
    }

    public void run() {
        System.out.println("A* Search is running..");
        tree.root.setDistance(tree.findPosition(tree.root).size() + findManhattanDistance());
        nodes = new PriorityQueue<>();
        nodes.add(tree.root);
        while (!nodes.isEmpty()) {
            solve(nodes.remove());
        }
    }

    public Double findManhattanDistance() {
        Double d1 = Double.valueOf(Math.abs(game.getPos(4,'x') - game.getPos(1,'x')) + Math.abs(game.getPos(4,'y') - game.getPos(1,'y')));
        Double d2 = Double.valueOf(Math.abs(game.getPos(4,'x') - game.getPos(2,'x')) + Math.abs(game.getPos(4,'y') - game.getPos(2,'y')));
        Double d3 = Double.valueOf(Math.abs(game.getPos(4,'x') - game.getPos(3,'x')) + Math.abs(game.getPos(4,'y') - game.getPos(3,'y')));
        return d1 + d2 + d3;
    }

    public Boolean solve(Node current) {
        checkReachedGoal(current);
        game.set(decodeArray(game, current.getGrid()));
        if (!(current.stringChildren.contains("up")) && (game.up() == true)) {
            Node newNode = tree.addNode(current,"up");
            newNode.setGrid(copyArray(game.grid));
            newNode.setDistance(tree.findPosition(newNode).size() + findManhattanDistance());
            nodes.add(newNode);
            counter ++;
            checkReachedGoal(newNode);
            game.down();
        }
        if ((!current.stringChildren.contains("down")) && (game.down() == true)) {
            Node newNode = tree.addNode(current,"down");
            newNode.setGrid(copyArray(game.grid));
            newNode.setDistance(tree.findPosition(newNode).size() + findManhattanDistance());
            nodes.add(newNode);
            counter ++;
            checkReachedGoal(newNode);
            game.up();
        }
        if ((!current.stringChildren.contains("right")) && (game.right() == true)) {
            Node newNode = tree.addNode(current,"right");
            newNode.setGrid(copyArray(game.grid));
            newNode.setDistance(tree.findPosition(newNode).size() + findManhattanDistance());
            nodes.add(newNode);
            counter ++;
            checkReachedGoal(newNode);
            game.left();
        }
        if ((!current.stringChildren.contains("left")) && (game.left() == true)) {
            Node newNode = tree.addNode(current,"left");
            newNode.setGrid(copyArray(game.grid));
            newNode.setDistance(tree.findPosition(newNode).size() + findManhattanDistance());
            nodes.add(newNode);
            counter ++;
            checkReachedGoal(newNode);
            game.right();
        }
        return false;
    }

}
