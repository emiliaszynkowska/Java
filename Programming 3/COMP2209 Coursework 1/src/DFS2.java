import java.util.*;
public class DFS2 extends Search {

    Stack stack;

    public DFS2() {
        super();
    }

    public void run() {
        System.out.println("Depth First Search 2 is running..");
        stack = new Stack();
        solve(tree.root);
        while (!stack.isEmpty()) {
            solve((Node)stack.pop());
        }
    }

    public Boolean solve(Node current) {
        try {
            checkReachedGoal(current);
            game.set(decodeArray(game, current.getGrid()));
            if (!(current.stringChildren.contains("up")) && (game.up() == true)) {
                Node newNode = tree.addNode(current, "up");
                newNode.setGrid(copyArray(game.grid));
                //System.out.println(DFSTree.findPosition(newNode));
                stack.push(newNode);
            }
            else if ((!current.stringChildren.contains("down")) && (game.down() == true)) {
                Node newNode = tree.addNode(current, "down");
                newNode.setGrid(copyArray(game.grid));
                //System.out.println(DFSTree.findPosition(newNode));
                stack.push(newNode);
            }
            else if ((!current.stringChildren.contains("right")) && (game.right() == true)) {
                Node newNode = tree.addNode(current, "right");
                newNode.setGrid(copyArray(game.grid));
                //System.out.println(DFSTree.findPosition(newNode));
                stack.push(newNode);
            }
            else if ((!current.stringChildren.contains("left")) && (game.left() == true)) {
                Node newNode = tree.addNode(current, "left");
                newNode.setGrid(copyArray(game.grid));
                //System.out.println(DFSTree.findPosition(newNode));
                stack.push(newNode);
            }
            else {
                stack.push(current.getParent());
            }
        } catch (Exception e) { System.out.println("Search failed"); }
        return false;
    }

}
