/**
 * CSE 222 Data Structures and Algorithms
 * Homework 04
 * Part 5
 * 161044004
 * @author Omer CEVIK
 */
public class Main
{
    /**
     * Main function to test.
     * @param args Not used.
     */
    public static void main(String[] args)
    {
        Integer[][] UserArray = new Integer[][]{{1, 2, 3, 4},
                                                {5, 6, 7, 8},
                                                {9, 10, 11, 12},
                                                {13, 14, 15, 16}};

        Array MainArray = new Array(UserArray);
        MainArray.printArray();

        Iterator iter = new Iterator();
        while(iter.hasNext())
        {
            System.out.printf("%d ",iter.next());
        }
        System.out.println();
    }
}