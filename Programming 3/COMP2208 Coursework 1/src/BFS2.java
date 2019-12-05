import java.util.LinkedList;
import java.util.Queue;
public class BFS2 extends Search {

    Queue<Node> queue;

    public BFS2() {
        super();
    }

    public void run() {
        System.out.println("Breadth First Search 2 is running..");
        game.set(new int[][] {
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 3, 5, 0},
                {2, 4, 0, 0}
            });
        tree.root.setGrid(copyArray(game.grid));
        queue = new LinkedList<>();
        solve(tree.root);
        while (!(queue.isEmpty())) {
            solve(queue.remove());
        }
    }

    public Boolean solve(Node current) {
        checkReachedGoal(current);
        if (!(current.stringChildren.contains("up up"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.up(4) == true && game.up(5) == true) {
                Node newNode = tree.addNode(current, "up up");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("up down"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.up(4) == true && game.down(5) == true) {
                Node newNode = tree.addNode(current, "up down");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("up right"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.up(4) == true && game.right(5) == true) {
                Node newNode = tree.addNode(current, "up right");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("up left"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.up(4) == true && game.left(5) == true) {
                Node newNode = tree.addNode(current, "up left");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("down up"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.down(4) == true && game.up(5) == true) {
                Node newNode = tree.addNode(current, "down up");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("down down"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.down(4) == true && game.down(5) == true) {
                Node newNode = tree.addNode(current, "down down");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("down right"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.down(4) == true && game.right(5) == true) {
                Node newNode = tree.addNode(current, "down right");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("down left"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.down(4) == true && game.left(5) == true) {
                Node newNode = tree.addNode(current, "down left");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("right up"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.right(4) == true && game.up(5) == true) {
                Node newNode = tree.addNode(current, "right up");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("right down"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.right(4) == true && game.down(5) == true) {
                Node newNode = tree.addNode(current, "right down");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("right right"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.right(4) == true && game.right(5) == true) {
                Node newNode = tree.addNode(current, "right right");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("right left"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.right(4) == true && game.left(5) == true) {
                Node newNode = tree.addNode(current, "right left");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("left up"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.left(4) == true && game.up(5) == true) {
                Node newNode = tree.addNode(current, "left up");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("left down"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.left(4) == true && game.down(5) == true) {
                Node newNode = tree.addNode(current, "left down");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("left right"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.left(4) == true && game.right(5) == true) {
                Node newNode = tree.addNode(current, "left right");
                newNode.setGrid(copyArray(game.grid));
                checkReachedGoal(newNode);
                queue.add(newNode);
                counter ++;
            }
        }
        if (!(current.stringChildren.contains("left left"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.left(4) == true && game.left(5) == true) {
                Node newNode = tree.addNode(current, "left left");
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
