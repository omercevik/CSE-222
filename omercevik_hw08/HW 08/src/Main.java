/**
 * CSE 222 Data Structures and Algorithms
 * Homework 08
 * 161044004
 * @author Omer CEVIK
 */
public class Main
{
    /**
     * Main driver class.
     * @param args Gets input file name.
     * @throws Exception Throws for file instructions.
     */
    public static void main(String[] args) throws Exception
    {
        // Creates adjacency list graph structure with reading file.
        Graph graph = new Graph(args[0]);

        // Prints popularity of graph nodes.
        graph.printPopular();
    }
}