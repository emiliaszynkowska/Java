import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
public class BFS {

    Queue<Node> queue;
    Game game;
    MyTree BFSTree;

    public BFS() {
        queue = new LinkedList<Node>();
        game = new Game();
        BFSTree = new MyTree();
        BFSTree.root.setGrid(copyArray(game.grid));
        checkReachedGoal(BFSTree.root);
        solve(BFSTree.root);
        while (!(queue.isEmpty())) {
            solve(queue.remove());
        }
    }

    public void checkReachedGoal(Node node) {
        if (game.reachedGoal()) {
            System.out.println("Solution reached");
            System.out.println(BFSTree.findPosition(node));
            System.exit(0);
        }
    }

    public Integer[][] copyArray(Integer[][] original) {
        if (original == null) {
            return null;
        }
        final Integer[][] newArray = new Integer[original.length][];
        for (int i=0; i<original.length; i++) {
            newArray[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return newArray;
    }

    public Boolean solve(Node current) {
        checkReachedGoal(current);
        //System.out.println(BFSTree.findPosition(current));
        if (!(current.stringChildren.contains("up"))) {
            game.set(copyArray(current.getGrid()));
            if(game.up() == true) {
                Node newNode = BFSTree.addNode(current, "up");
                newNode.setGrid(copyArray(game.grid));
                //System.out.println(BFSTree.findPosition(newNode));
                checkReachedGoal(newNode);
                queue.add(newNode);
            }
        }
        if ((!current.stringChildren.contains("down"))) {
            game.set(copyArray(current.getGrid()));
            if(game.down() == true) {
                Node newNode = BFSTree.addNode(current, "down");
                newNode.setGrid(copyArray(game.grid));
                //System.out.println(BFSTree.findPosition(newNode));
                checkReachedGoal(newNode);
                queue.add(newNode);
            }
        }
        if ((!current.stringChildren.contains("right"))) {
            game.set(copyArray(current.getGrid()));
            if(game.right() == true) {
                Node newNode = BFSTree.addNode(current, "right");
                newNode.setGrid(copyArray(game.grid));
                //System.out.println(BFSTree.findPosition(newNode));
                checkReachedGoal(newNode);
                queue.add(newNode);
            }
        }
        if ((!current.stringChildren.contains("left"))) {
            game.set(copyArray(current.getGrid()));
            if(game.left() == true) {
                Node newNode = BFSTree.addNode(current, "left");
                newNode.setGrid(copyArray(game.grid));
                //System.out.println(BFSTree.findPosition(newNode));
                checkReachedGoal(newNode);
                queue.add(newNode);
            }
        }
        else {
            queue.add(current.getParent());
        }
        return false;
    }



}
