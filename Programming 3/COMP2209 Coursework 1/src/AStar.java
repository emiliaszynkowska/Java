import java.util.Arrays;
import java.util.PriorityQueue;

public class AStar {

    Game game;
    MyTree ATree;
    PriorityQueue<Node> nodes;

    public AStar() {
        game = new Game();
        ATree = new MyTree();
        nodes = new PriorityQueue<>();
        nodes.add(ATree.root);
        ATree.root.setGrid(copyArray(game.grid));
        ATree.root.setDistance(ATree.findPosition(ATree.root).size() + findDistance());
        checkReachedGoal(ATree.root);
        while (!nodes.isEmpty()) {
            solve(nodes.remove());
        }

    }

    public void checkReachedGoal(Node node) {
        if (game.reachedGoal()) {
            System.out.println("Solution reached");
            System.out.println(ATree.findPosition(node));
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

    public Integer findDistance() {
        Integer d1 = Math.abs(game.getPos(4,'x') - game.getPos(1,'x')) + Math.abs(game.getPos(4,'y') - game.getPos(1,'x'));
        Integer d2 = Math.abs(game.getPos(4,'x') - game.getPos(2,'x')) + Math.abs(game.getPos(4,'y') - game.getPos(2,'x'));
        Integer d3 = Math.abs(game.getPos(4,'x') - game.getPos(3,'x')) + Math.abs(game.getPos(4,'y') - game.getPos(3,'x'));
        return d1 + d2 + d3;
    }

    public Boolean solve(Node current) {
//        try {
//            Thread.sleep(500);
//        } catch (Exception e) {}
        checkReachedGoal(current);
        //System.out.println(ATree.findPosition(current));
        game.set(current.getGrid());
        if (!(current.stringChildren.contains("up")) && (game.up() == true)) {
            Node newNode = ATree.addNode(current,"up");
            newNode.setGrid(copyArray(game.grid));
            newNode.setDistance(ATree.findPosition(newNode).size() + findDistance());
            nodes.add(newNode);
            game.down();
        }
        if ((!current.stringChildren.contains("down")) && (game.down() == true)) {
            Node newNode = ATree.addNode(current,"down");
            newNode.setGrid(copyArray(game.grid));
            newNode.setDistance(ATree.findPosition(newNode).size() + findDistance());
            nodes.add(newNode);
            game.up();
        }
        if ((!current.stringChildren.contains("right")) && (game.right() == true)) {
            Node newNode = ATree.addNode(current,"right");
            newNode.setGrid(copyArray(game.grid));
            newNode.setDistance(ATree.findPosition(newNode).size() + findDistance());
            nodes.add(newNode);
            game.left();
        }
        if ((!current.stringChildren.contains("left")) && (game.left() == true)) {
            Node newNode = ATree.addNode(current,"left");
            newNode.setGrid(copyArray(game.grid));
            newNode.setDistance(ATree.findPosition(newNode).size() + findDistance());
            nodes.add(newNode);
            game.right();
        }
        return false;
    }

}
