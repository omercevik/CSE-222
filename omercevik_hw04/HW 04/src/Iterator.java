/**
 * Iterator class implementation.
 */
class Iterator extends Array
{
    private int index = 0;
    private Integer[] RecursiveArray;
    private int colSize = A[0].length;
    private int rowSize = A.length;
    private int size = colSize*rowSize;
    private int it = 0;
    private int next;

    /**
     * Constructor.
     */
    Iterator()
    {
        RecursiveArray = new Integer[size+1];
        iterateRecursively(0,0,0,colSize-1,rowSize-1,0);
        next = RecursiveArray[0];
        RecursiveArray[size] = -999;
    }

    /**
     *
     * @return Checks next.
     */
    boolean hasNext()
    {
        if (next == -999)
            return false;
        return true;
    }

    /**
     *
     * @return Returns next if it is exist.
     */
    Integer next()
    {
        if (hasNext())
        {
            int temp = next;
            next = RecursiveArray[++it];
            return temp;
        }
        else
            throw new java.util.NoSuchElementException();
    }

    /**
     *
     * @param row   CurrentRow
     * @param col   CurrentColumn
     * @param turn  Which side goes.
     * @param cSize MaxColumnSize
     * @param rSize MaxRowSize
     * @param k     Inside the shape.
     */
    private void iterateRecursively(int row, int col, int turn, int cSize, int rSize, int k)
    {
        if (index == size-1)
        {
            RecursiveArray[index] = A[row][col];
        }
        else
        {
            if (turn == 0)
            {
                while(col < cSize)
                {
                    RecursiveArray[index++] = A[row][col++];
                }
                turn = 1;
            }
            else if(turn == 1)
            {
                while(row < rSize)
                {
                    RecursiveArray[index++] = A[row++][col];
                }
                turn = 2;
            }
            else if(turn == 2)
            {
                while(col > k)
                {
                    RecursiveArray[index++] = A[row][col--];
                }
                turn = 3;
                --cSize;
            }
            else if(turn == 3)
            {
                ++k;
                while(row > k)
                {
                    RecursiveArray[index++] = A[row--][col];
                }
                turn = 0;
                --rSize;
            }
            iterateRecursively(row,col,turn,cSize,rSize,k);
        }
    }
}
