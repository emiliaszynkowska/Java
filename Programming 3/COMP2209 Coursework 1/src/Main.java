import java.util.Scanner;

public class Main {

    public static void main (String[] args) {

        System.out.println("Select search method: \n1 Breadth First Search \n2 Depth First Search \n3 Depth First Search 2 \n4 Iterative Deepening Search \n5 A* Search");
        Scanner scanner = new Scanner(System.in);
        Integer input = scanner.nextInt();
        if (input == 1) {
            BFS bfs = new BFS();
            bfs.run();
        }
        else if (input == 2) {
            DFS dfs = new DFS();
            dfs.run();
        }
        else if (input == 3) {
            DFS2 dfs2 = new DFS2();
            dfs2.run();
        }
        else if (input == 4) {
            IDS ids = new IDS();
            ids.run();
        }
        else if (input == 5) {
            AStar astar = new AStar();
            astar.run();
        }
        else {
            System.out.println("Invalid input");
        }

    }

}