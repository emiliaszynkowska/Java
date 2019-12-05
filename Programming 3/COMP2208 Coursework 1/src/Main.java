import java.util.Scanner;

public class Main {

    public static void main (String[] args) {

        System.out.println("Select search method: \n1 Breadth First Search \n2 Depth First Search \n3 Depth First Search 2 \n4 Iterative Deepening Search \n5 A* Search \n6 Extension - BFS2");
        Scanner scanner = new Scanner(System.in);
        Integer input1 = scanner.nextInt();
        System.out.println("Select mode: \n1 All results printed out (slower) \n2 Only solution printed out (faster)");
        Integer input2 = scanner.nextInt();
        if (input1 == 1) {
            BFS bfs = new BFS();
            if (input2 == 1)
                bfs.print = true;
            bfs.run();
        }
        else if (input1 == 2) {
            DFS dfs = new DFS();
            if (input2 == 1)
                dfs.print = true;
            dfs.run();
        }
        else if (input1 == 3) {
            DFS2 dfs2 = new DFS2();
            if (input2 == 1)
                dfs2.print = true;
            dfs2.run();
        }
        else if (input1 == 4) {
            IDS ids = new IDS();
            if (input2 == 1)
                ids.print = true;
            ids.run();
        }
        else if (input1 == 5) {
            AStar astar = new AStar();
            if (input2 == 1)
                astar.print = true;
            astar.run();
        }
        else if (input1 == 6) {
            BFS2 bfs2 = new BFS2();
            if (input2 == 1)
                bfs2.print = true;
            bfs2.run();
        }
        else {
            System.out.println("Invalid input");
        }

    }

}