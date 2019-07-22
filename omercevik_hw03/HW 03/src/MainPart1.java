import java.io.*;

/**
 * CSE 222 Data Structures and Algorithms
 * Homework 03
 * Part 1
 * 161044004
 * @author Omer Cevik
 */
public class MainPart1
{
    private static int IMAGE_ROW = 0;
    private static int IMAGE_COL = 0;

    /**
     *  Checks valid position.
     * @param ImageArray Image saved in array.
     * @param CurrentRow Image's current row index.
     * @param CurrentColumn Image's current column index.
     * @param Passed Saves did we checked the position.
     * @return Checks valid position.
     */
    private static boolean CheckPosition(int ImageArray[][], int CurrentRow, int CurrentColumn, boolean Passed[][])
    {
        return (CurrentRow >= 0) && (CurrentRow < IMAGE_ROW) && (CurrentColumn >= 0) &&
                (CurrentColumn < IMAGE_COL) && (ImageArray[CurrentRow][CurrentColumn] == 1 && !Passed[CurrentRow][CurrentColumn]);
    }

    /**
     *  Finds the sets of image.
     * @param ImageArray Image saved in array.
     * @param CurrentRow Image's current row index.
     * @param CurrentColumn Image's current column index.
     * @param Passed Saves did we checked the position.
     */
    private static void FindSets(int ImageArray[][], int CurrentRow, int CurrentColumn, boolean Passed[][])
    {
        int CurrentRowNeighbor[] = new int[] {-1,  0, 0, 1};
        int CurrentColumnNeighbor[] = new int[] {0, -1, 1, 0};
        boolean StackControlFlag = true;
        Stack<Integer> X = new Stack();
        Stack<Integer> Y = new Stack();

        Passed[CurrentRow][CurrentColumn] = true;

        while(StackControlFlag)
        {
            if (CheckPosition(ImageArray, CurrentRow-1, CurrentColumn, Passed))
            {
                X.push(CurrentColumn);
                Y.push(CurrentRow-1);
                Passed[CurrentRow-1][CurrentColumn] = true;
            }
            if (CheckPosition(ImageArray, CurrentRow, CurrentColumn-1, Passed))
            {
                X.push(CurrentColumn-1);
                Y.push(CurrentRow);
                Passed[CurrentRow][CurrentColumn-1] = true;
            }
            if (CheckPosition(ImageArray, CurrentRow, CurrentColumn+1, Passed))
            {
                X.push(CurrentColumn+1);
                Y.push(CurrentRow);
                Passed[CurrentRow][CurrentColumn+1] = true;
            }
            if (CheckPosition(ImageArray, CurrentRow+1, CurrentColumn, Passed))
            {
                X.push(CurrentColumn);
                Y.push(CurrentRow+1);
                Passed[CurrentRow+1][CurrentColumn] = true;
            }
            if (!X.isEmpty())
            {
                StackControlFlag = true;
                CurrentRow = Y.pop();
                CurrentColumn = X.pop();
            }
            else
            {
                StackControlFlag = false;
            }
        }
    }

    /**
     *  Searches sets in image.
     * @param ImageArray Image saved in array.
     * @return Returns set numbers.
     */
    public static int SetsCounterFunction(int ImageArray[][])
    {
        boolean Passed[][] = new boolean[IMAGE_ROW][IMAGE_COL];

        int SetsCount = 0;
        for (int i = 0; i < IMAGE_ROW; ++i)
        {
            for (int j = 0; j < IMAGE_COL; ++j)
            {
                if (!Passed[i][j] && ImageArray[i][j] == 1)
                {
                    FindSets(ImageArray, i, j, Passed);
                    ++SetsCount;
                }
            }
        }

        return SetsCount;
    }

    /**
     *  Main function of Part1.
     * @param args Not used.
     * @throws Exception About file.
     */
    public static void main(String[] args) throws Exception
    {
        String filename = "test_file_part1.txt";

        FileReader fr = new FileReader(filename);
        int FileChar = 0;
        int RowCountOfImage = 1, ColumnCountOfImage = 0;

        while ((FileChar = fr.read()) != -1)
        {
            int data = (char)FileChar - '0';

            if ( data == 0 || data == 1 )
            {
                ++ColumnCountOfImage;
            }
            if ( (char)FileChar == '\n' )
            {
                ++RowCountOfImage;
                ColumnCountOfImage = 0;
            }
        }
        fr.close();

        fr = new FileReader(filename);

        int[][] ImageArray = new int[RowCountOfImage][ColumnCountOfImage];

        RowCountOfImage = 0;
        ColumnCountOfImage = 0;

        while ((FileChar = fr.read()) != -1)
        {
            int data = (char)FileChar - '0';
            if ( data == 0 || data == 1 )
            {
                ImageArray[RowCountOfImage][ColumnCountOfImage] = data;
                System.out.printf("%d ",data);
                ++ColumnCountOfImage;
            }
            if ( (char)FileChar == '\n' )
            {
                System.out.println();
                ++RowCountOfImage;
                ColumnCountOfImage = 0;
            }
        }
        fr.close();

        IMAGE_ROW = RowCountOfImage+1;
        IMAGE_COL = ColumnCountOfImage;
        System.out.println();
        System.out.println();
        System.out.println("There are totally " + SetsCounterFunction(ImageArray) + " sets in image.");
    }
}