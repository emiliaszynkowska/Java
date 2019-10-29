import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class SPTTest {

    SPT spt;
    int[][] edges;
    int source;
    int[] result;

    @BeforeEach
    @DisplayName("Setting up")
    void setUp() {
        try {
            spt = new SPT();
        } catch (Exception e) {
            System.out.println("The SPT file could not be found.");
        }
    }

    @Nested
    @DisplayName("Testing exceptions")
    class Test1 {
        @Test
        @DisplayName("Invalid graph test")
        void test1part1() {
            edges = new int[][] {
                {-1, 0, 2},
                {1, -1, 5}
            };
            source = 0;
            Assertions.assertThrows(InvalidGraphException.class, () -> {
                result = spt.findSPT(edges, source);
            });
        }
        @Test
        @DisplayName("Invalid source test when source < 0")
        void test1part2() {
            edges = new int[][] {
                {-1, 0},
                {0, -1}
            };
            source = -5;
            Assertions.assertThrows(InvalidSourceException.class, () -> {
                result = spt.findSPT(edges, source);
            });
        }
        @Test
        @DisplayName("Invalid source test when source > length")
        void test1part3() {
            edges = new int[][] {
                {-1, 0},
                {0, -1}
            };
            source = 10;
            Assertions.assertThrows(InvalidSourceException.class, () -> {
                result = spt.findSPT(edges, source);
            });
        }
        @Test
        @DisplayName("Loop detected test")
        void test1part4() {
            edges = new int[][] {
                {0, 0},
                {0, 0}
            };
            source = 0;
            Assertions.assertThrows(LoopDetectedException.class, () -> {
                result = spt.findSPT(edges, source);
            });
        }
        @Test
        @DisplayName("No path test")
        void test1part5() {
            edges = new int[][] {
                {-1, -1},
                {-1, -1}
            };
            source = 0;
            Assertions.assertThrows(NoPathException.class, () -> {
                result = spt.findSPT(edges, source);
            });
        }
        @Test
        @DisplayName("Null edges test")
        void test1part6() {
            edges = null;
            source = 0;
            Assertions.assertThrows(NullPointerException.class, () -> {
                result = spt.findSPT(edges, source);
            });
        }
        @Test
        @DisplayName("Invalid length test")
        void test1part7() {
            edges = new int[][] {
                    {-1, 1, 4, -1},
                    {-1, -1, 2, 2},
                    {-1, -1, -1, 1},
                    {-1, -1, -1, -1}
            };
            source = 0;
            try {
                result = spt.findSPT(edges, source);
            } catch (Exception e) {}
            int[] expected = {-1, 0, 1, 1};
            assertArrayEquals("Checking the result", expected, result);
        }
    }

    @Nested
    @DisplayName("Testing findSPT()")
    class Test2 {
        @Test
        @DisplayName("Testing a simple graph")
        void test2part1() {
            edges = new int[][] {
                {-1, 1, 4, -1},
                {-1, -1, 2, 2},
                {-1, -1, -1, 1},
                {-1, -1, -1, -1}
            };
            source = 0;
            try {
                result = spt.findSPT(edges, source);
            } catch (Exception e) {}
            int[] expected = {-1, 0, 1, 1};
            assertArrayEquals("Checking the result", expected, result);
        }
        @Test
        @DisplayName("Testing a square graph")
        void test2part2() {
            edges = new int[][] {
                {-1,3,2,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,2,7,-1,-1,-1,-1},
                {-1,-1,-1,-1,4,10,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,1,-1,-1},
                {-1,-1,-1,-1,-1,-1,1,5,-1},
                {-1,-1,-1,-1,-1,-1,-1,9,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,1},
                {-1,-1,-1,-1,-1,-1,-1,-1,6},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1}
            };
            source = 0;
            try {
                result = spt.findSPT(edges, source);
            } catch (Exception e) {}
            int[] expected = {-1, 0, 0, 1, 2, 2, 3, 4, 6};
            assertArrayEquals("Checking the result", expected, result);
        }
        @Test
        @DisplayName("Testing a graph with one path")
        void test2part3() {
            edges = new int[][] {
                {-1,3,-1},
                {-1,-1,3},
                {-1,-1,-1}
            };
            source = 0;
            try {
                result = spt.findSPT(edges, source);
            } catch (Exception e) {}
            int[] expected = {-1,0,1};
            assertArrayEquals("Checking the result", expected, result);
        }
        @Test
        @DisplayName("Testing a graph with 2 possible solutions")
        void test2part4() {
            edges = new int[][] {
                {-1,1,-1,-1,2},
                {-1,-1,1,-1,-1},
                {-1,-1,-1,1,-1},
                {-1,-1,-1,-1,-1},
                {-1,-1,-1,1,-1}
            };
            source = 0;
            try {
                result = spt.findSPT(edges, source);
            } catch (Exception e) {}
            boolean correct = false;
            int[] expected1 = {-1,0,1,4,0};
            int[] expected2 = {-1,0,1,2,0};
            if(Arrays.equals(expected1, result) || Arrays.equals(expected2, result))
                correct = true;
            Assertions.assertTrue(correct == true);
        }
        @Test
        @DisplayName("Testing a graph with one node")
        void test2part5() {
            edges = new int[][] {
                    {0}
            };
            source = 0;
            Assertions.assertThrows(LoopDetectedException.class, () -> {
                result = spt.findSPT(edges, source);
            });
        }
    }

}