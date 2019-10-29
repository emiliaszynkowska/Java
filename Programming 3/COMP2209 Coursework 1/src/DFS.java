import java.util.Arrays;

public class DFS {

    Game game;
    MyTree DFSTree;
    Integer depth;

    public DFS() {
        game = new Game();
        DFSTree = new MyTree();
        DFSTree.root.setGrid(copyArray(game.grid));
        solve(DFSTree.root);
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
        System.out.println(DFSTree.findPosition(current));
        checkReachedGoal(current);
        if (!(current.stringChildren.contains("up"))) {
            game.set(copyArray(current.getGrid()));
            if(game.up() == true) {
                Node newNode = DFSTree.addNode(current, "up");
                newNode.setGrid(copyArray(game.grid));
                solve(newNode);
            }
        }
        if (!(current.stringChildren.contains("down")) && current.stringChildren.contains("up")) {
            game.set(copyArray(current.getGrid()));
            if(game.down() == true) {
                Node newNode = DFSTree.addNode(current, "down");
                newNode.setGrid(copyArray(game.grid));
                solve(newNode);
            }
        }
        if (!(current.stringChildren.contains("right")) && current.stringChildren.contains("up") && current.stringChildren.contains("down")) {
            game.set(copyArray(current.getGrid()));
            if(game.right() == true) {
                Node newNode = DFSTree.addNode(current, "right");
                newNode.setGrid(copyArray(game.grid));
                solve(newNode);
            }
        }
        if (!(current.stringChildren.contains("left")) && current.stringChildren.contains("up") && current.stringChildren.contains("down") && current.stringChildren.contains("right")) {
            game.set(copyArray(current.getGrid()));
            if(game.left() == true) {
                Node newNode = DFSTree.addNode(current, "left");
                newNode.setGrid(copyArray(game.grid));
                solve(newNode);
            }
        }
        return false;
    }

}
