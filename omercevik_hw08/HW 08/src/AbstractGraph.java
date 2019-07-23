import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * AbstractGraph class.
 */
abstract class AbstractGraph
{
    private int numV;
    String[][] GraphArray;

    /**
     * AbstractGraph constructor.
     */
    AbstractGraph(){}

    /**
     *
     * @return Returns number of nodes.
     */
    int getNumV() {
        return numV;
    }

    /**
     *
     * @param filename Gets filename.
     * @throws Exception Throws for file instructions.
     */
    void readFile(String filename) throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
        String line = br.readLine();
        String[] tokens = line.split("  ");
        numV = Integer.parseInt(tokens[0]);
        GraphArray = new String[numV][2];

        for (int i = 0; (line = br.readLine()) != null; ++i)
        {
            tokens = line.split("  ");
            GraphArray[i][0] = tokens[0];
            GraphArray[i][1] = tokens[1];
        }
    }
}