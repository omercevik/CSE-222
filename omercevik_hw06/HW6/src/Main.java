import java.io.*;

/**
 * CSE 222 Data Structures and Algorithms
 * Homework 06
 * 161044004
 * @author Omer CEVIK
 */
public class Main
{
    /**
     * Driver class method.
     * @param args Not used.
     * @throws Exception Throws file exception.
     */
    public static void main(String[] args) throws Exception
    {
        NLP obj = new NLP();
        obj.readDataset("dataset");

        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String line;
        while((line = br.readLine()) != null)
        {
            String[] tokens = line.split(" ");
            if (tokens.length == 2)
            {
                System.out.println(obj.bigrams(tokens[1]));
            }
            else
            {
                System.out.println(obj.tfIDF(tokens[1],tokens[2]));
            }
        }

        obj.printWordMap();
    }
}