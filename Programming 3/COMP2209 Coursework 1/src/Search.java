import java.util.ArrayList;
import java.util.Arrays;

public class Search {

    Game game;
    MyTree tree;

    public Search() {
        game = new Game();
        tree = new MyTree();
        tree.root.setGrid(copyArray(game.grid));
    }

    public void checkReachedGoal(Node node) {
        if (game.reachedGoal()) {
            System.out.println("Solution reached");
            System.out.println(tree.findPosition(node));
            System.exit(0);
        }
    }

    public int[][] copyArrayOld(int[][] original) {
        final int[][] newArray = new int[4][4];
        for (int i=0; i<4; i++) {
            newArray[i] = Arrays.copyOf(original[i],4);
        }
        return newArray;
    }
    public String copyArray(int[][] original) {
        String copy = "";
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                copy = copy + original[i][j];
            }
        }
        return copy;
    }
    public int[][] decodeArray(Game game, String code) {
        int[][] newGrid = new int[4][4];
        for (int i=0; i<4; i++) {
            newGrid[0][i] = (Character.getNumericValue(code.charAt(i)));
        }
        for (int i=0; i<4; i++) {
            newGrid[1][i] = (Character.getNumericValue(code.charAt(i+4)));
        }
        for (int i=0; i<4; i++) {
            newGrid[2][i] = (Character.getNumericValue(code.charAt(i+8)));
        }
        for (int i=0; i<4; i++) {
            newGrid[3][i] = (Character.getNumericValue(code.charAt(i+12)));
        }
        return newGrid;
    }

}
