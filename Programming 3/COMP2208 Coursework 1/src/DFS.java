public class DFS extends Search {

    public DFS() {
        super();
    }

    public void run() {
        System.out.println("Depth First Search is running..");
        solve(tree.root);
    }

    public Boolean solve(Node current) {
        checkReachedGoal(current);
        if (!(current.stringChildren.contains("up"))) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.up() == true) {
                Node newNode = tree.addNode(current, "up");
                newNode.setGrid(copyArray(game.grid));
                counter ++;
                solve(newNode);
            }
        }
        if (!(current.stringChildren.contains("down")) && current.stringChildren.contains("up")) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.down() == true) {
                Node newNode = tree.addNode(current, "down");
                newNode.setGrid(copyArray(game.grid));
                counter ++;
                solve(newNode);
            }
        }
        if (!(current.stringChildren.contains("right")) && current.stringChildren.contains("up") && current.stringChildren.contains("down")) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.right() == true) {
                Node newNode = tree.addNode(current, "right");
                newNode.setGrid(copyArray(game.grid));
                counter ++;
                solve(newNode);
            }
        }
        if (!(current.stringChildren.contains("left")) && current.stringChildren.contains("up") && current.stringChildren.contains("down") && current.stringChildren.contains("right")) {
            game.set(decodeArray(game, current.getGrid()));
            if(game.left() == true) {
                Node newNode = tree.addNode(current, "left");
                newNode.setGrid(copyArray(game.grid));
                counter ++;
                solve(newNode);
            }
        }
        else {
            try {
                solve(current.getParent());
            }
            catch (NullPointerException n) {
                System.out.println("Search failed");
            }
        }
        return false;
    }

}
