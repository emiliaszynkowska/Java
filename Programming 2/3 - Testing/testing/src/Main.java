public class Main {

    public static void main(String[] args) {

        int[][] edges;
        int source;
        int[] result = null;

        edges = new int[][] {
                {0}
        };
        source = 0;

        SPT spt = new SPT();
        try {
            result = spt.findSPT(edges, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());
    }

}
