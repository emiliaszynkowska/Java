import java.util.*;
public class DFSGraph {

    Game game;
    MyTree DFSTree;
    Stack stack;

    public DFSGraph() {
        game = new Game();
        DFSTree = new MyTree();
        DFSTree.root.setGrid(copyArray(game.grid));
        stack = new Stack();
        solve(DFSTree.root);
        while (!stack.isEmpty()) {
            solve((Node)stack.pop());
        }
    }

    public void checkReachedGoal(Node node) {
        if (game.reachedGoal()) {
            System.out.println("Solution reached");
            System.out.println(DFSTree.findPosition(node));
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
        game.set(current.getGrid());
        if (!(current.stringChildren.contains("up")) && (game.up() == true)) {
            Node newNode = DFSTree.addNode(current, "up");
            newNode.setGrid(game.grid);
            System.out.println(DFSTree.findPosition(newNode));
            stack.push(newNode);
        }
        else if ((!current.stringChildren.contains("down")) && (game.down() == true)) {
            Node newNode = DFSTree.addNode(current, "down");
            newNode.setGrid(game.grid);
            System.out.println(DFSTree.findPosition(newNode));
            stack.push(newNode);
        }
        else if ((!current.stringChildren.contains("right")) && (game.right() == true)) {
            Node newNode = DFSTree.addNode(current, "right");
            newNode.setGrid(game.grid);
            System.out.println(DFSTree.findPosition(newNode));
            stack.push(newNode);
        }
        else if ((!current.stringChildren.contains("left")) && (game.left() == true)) {
            Node newNode = DFSTree.addNode(current, "left");
            newNode.setGrid(game.grid);
            System.out.println(DFSTree.findPosition(newNode));
            stack.push(newNode);
        }
        else {
            stack.push(current.getParent());
        }
        return false;
    }

}
