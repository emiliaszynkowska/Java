import java.util.Arrays;

public class Game {

    int[][] grid;

    public Game() {
        grid = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 2, 3, 4}
        };
    }

    public void set(int[][] newGrid) {
        grid = newGrid;
    }

    public void printGrid() {
//        for (int i=0; i<=3; i++) {
//            System.out.println(Arrays.asList(grid[i]));
//        }
//        System.out.println();
    }

    public Boolean reachedGoal() {
        if ((grid[1][1] == 1) && (grid[2][1] == 2) && (grid[3][1] == 3))
            return true;
        return false;
    }

    public Integer getPos(int counter, Character axis) {
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (grid[i][j] == counter) {
                    if (axis == 'x')
                        return j;
                    else if (axis == 'y') {
                        return i;
                    }
                }}} return null; }


    public Boolean up() {
        int x = getPos(4,'x');
        int y = getPos(4,'y');
        if ((y-1) >= 0) {
            grid[y][x] = grid[y-1][x];
            grid[y-1][x] = 4;
            printGrid();
            return true;
        }
        return false;
    }
    public Boolean down() {
        int x = getPos(4,'x');
        int y = getPos(4,'y');
        if ((y+1) <= 3) {
            grid[y][x] = grid[y+1][x];
            grid[y+1][x] = 4;
            printGrid();
            return true;
        }
        return false;
    }
    public Boolean right() {
        int x = getPos(4,'x');
        int y = getPos(4,'y');
        if ((x+1) <= 3) {
            grid[y][x] = grid[y][x+1];
            grid[y][x+1] = 4;
            printGrid();
            return true;
        }
        return false;
    }
    public Boolean left() {
        int x = getPos(4,'x');
        int y = getPos(4,'y');
        if ((x-1) >= 0) {
            grid[y][x] = grid[y][x-1];
            grid[y][x-1] = 4;
            printGrid();
            return true;
        }
        return false;
    }

}
