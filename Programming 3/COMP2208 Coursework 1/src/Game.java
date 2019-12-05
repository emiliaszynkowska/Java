public class Game {

    int[][] grid;

    public Game() {
        grid = new int[][] {
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {4, 0, 2, 0},
                {0, 3, 0, 0}
        };
    }

    public int[][] getGrid() {
        return grid;
    }
    public void set(int[][] newGrid) {
        grid = newGrid;
    }

    public void printGrid() {
        for (int i=0; i<4; i++) {
            System.out.println("|"+grid[i][0]+"|"+grid[i][1]+"|"+grid[i][2]+"|"+grid[i][3]+"|");
        }
        System.out.println();
    }

    public Boolean reachedGoal() {
        if ((grid[1][1] == 1) && (grid[2][1] == 2) && (grid[3][1] == 3))
            return true;
        return false;
    }

    public Integer getPos(int counter, Character axis) {
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
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
            return true;
        }
        return false;
    }

    public Boolean up(int counter) {
        int x = getPos(counter,'x');
        int y = getPos(counter,'y');
        if ((y-1) >= 0) {
            grid[y][x] = grid[y-1][x];
            grid[y-1][x] = counter;
            return true;
        }
        return false;
    }
    public Boolean down(int counter) {
        int x = getPos(counter,'x');
        int y = getPos(counter,'y');
        if ((y+1) <= 3) {
            grid[y][x] = grid[y+1][x];
            grid[y+1][x] = counter;
            return true;
        }
        return false;
    }
    public Boolean right(int counter) {
        int x = getPos(counter,'x');
        int y = getPos(counter,'y');
        if ((x+1) <= 3) {
            grid[y][x] = grid[y][x+1];
            grid[y][x+1] = counter;
            return true;
        }
        return false;
    }
    public Boolean left(int counter) {
        int x = getPos(counter,'x');
        int y = getPos(counter,'y');
        if ((x-1) >= 0) {
            grid[y][x] = grid[y][x-1];
            grid[y][x-1] = counter;
            return true;
        }
        return false;
    }
}
