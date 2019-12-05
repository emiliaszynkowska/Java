import java.util.Stack;

public class IDS extends Search {

    Stack stack;
    int depth;

    public IDS() {
        super();
    }
    
    public void run() {
        depth = 1;
        while (true) {
            iterate(depth);
            depth++;
        }
    }

    public void iterate(int depth) {
        game = new Game();
        tree = new Tree();
        tree.root.setGrid(copyArray(game.grid));
        stack = new Stack();
        solve(tree.root, depth);
        while (!stack.isEmpty()) {
            solve((Node)stack.pop(),depth);
        }
        System.out.println("Iteration at depth " + depth + " complete");

    }

    public Boolean solve(Node current, int depth) {
        if (tree.findPosition(current).size() <= depth) {
            checkReachedGoal(current);
            game.set(decodeArray(game, current.getGrid()));
            if (!current.stringChildren.contains("up") && game.up() == true) {
                Node newNode = tree.addNode(current, "up");
                newNode.setGrid(copyArray(game.grid));
                stack.push(newNode);
                counter ++;
                checkReachedGoal(newNode);
            }
            else if (!current.stringChildren.contains("down") && game.down() == true) {
                Node newNode = tree.addNode(current, "down");
                newNode.setGrid(copyArray(game.grid));
                stack.push(newNode);
                counter ++;
                checkReachedGoal(newNode);
            }
            else if (!current.stringChildren.contains("right") && game.right() == true) {
                Node newNode = tree.addNode(current, "right");
                newNode.setGrid(copyArray(game.grid));
                stack.push(newNode);
                counter ++;
                checkReachedGoal(newNode);
            }
            else if (!current.stringChildren.contains("left") && game.left() == true) {
                Node newNode = tree.addNode(current, "left");
                newNode.setGrid(copyArray(game.grid));
                stack.push(newNode);
                counter ++;
                checkReachedGoal(newNode);
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
