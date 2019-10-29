import java.util.Arrays;
import java.util.Stack;

public class IDS {

    Game game;
    MyTree DFSTree;
    Stack stack;
    Integer depth;

    public IDS() {
       depth = 1;
       while (true) {
           iterate(depth);
           depth++;
       }
    }

    public void iterate(Integer depth) {
        game = new Game();
        DFSTree = new MyTree();
        DFSTree.root.setGrid(copyArray(game.grid));
        stack = new Stack();
        solve(DFSTree.root, depth);
        while (!stack.isEmpty()) {
            solve((Node)stack.pop(), depth);
        }
        System.out.println("Iteration at depth " + depth + " complete");

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

    public Boolean solve(Node current, Integer depth) {
        if (DFSTree.findPosition(current).size() <= depth) {
            checkReachedGoal(current);
            game.set(current.getGrid());
            if (!current.stringChildren.contains("up") && game.up() == true) {
                Node newNode = DFSTree.addNode(current, "up");
                //System.out.println(DFSTree.findPosition(newNode));
                newNode.setGrid(copyArray(game.grid));
                stack.push(newNode);
                game.down();
            }
            else if (!current.stringChildren.contains("down") && game.down() == true) {
                Node newNode = DFSTree.addNode(current, "down");
                //System.out.println(DFSTree.findPosition(newNode));
                newNode.setGrid(copyArray(game.grid));
                stack.push(newNode);
                game.up();
            }
            else if (!current.stringChildren.contains("right") && game.right() == true) {
                Node newNode = DFSTree.addNode(current, "right");
                //System.out.println(DFSTree.findPosition(newNode));
                newNode.setGrid(copyArray(game.grid));
                stack.push(newNode);
                game.left();
            }
            else if (!current.stringChildren.contains("left") && game.left() == true) {
                Node newNode = DFSTree.addNode(current, "left");
                //System.out.println(DFSTree.findPosition(newNode));
                newNode.setGrid(copyArray(game.grid));
                stack.push(newNode);
                game.right();
            }
            else {
                if (current.getParent() != null)
                    stack.push(current.getParent());
            }
        }
        else {
            if (current.getParent() != null)
                stack.push(current.getParent());
        }
        return false;
    }

}
