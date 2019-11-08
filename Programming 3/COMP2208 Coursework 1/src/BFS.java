import java.util.LinkedList;
import java.util.Queue;
public class BFS extends Search {

    Queue<Node> queue;

    public BFS() {
        super();
    }

    public void run() {
        System.out.println("Breadth First Search is running..");
        queue = new LinkedList<Node>();
        solve(tree.root);
        while (!(queue.isEmpty())) {
            solve(queue.remove());
        }
    }

    public Boolean solve(Node current) {
        checkReachedGoal(current);
        if (!(current.stringChildren.contains("up"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.up() == true) {
                Node newNode = tree.addNode(current, "up");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if ((!current.stringChildren.contains("down"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.down() == true) {
                Node newNode = tree.addNode(current, "down");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if ((!current.stringChildren.contains("right"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.right() == true) {
                Node newNode = tree.addNode(current, "right");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if ((!current.stringChildren.contains("left"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.left() == true) {
                Node newNode = tree.addNode(current, "left");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        else {
            queue.add(current.getParent());
        }
        return false;
    }



}
