/**
 * Basic integer array class.
 */
class Array
{
    Integer[][] A;

    /**
     * Constructor creates 2D array.
     */
    Array()
    {
        A = new Integer[][]{{1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16}};
    }

    Array(Integer[][] User)
    {
        A = User;
    }

    /**
     * Just prints 2D array.
     */
    void printArray()
    {
        for (int i = 0; i < A.length; ++i)
        {
            for (int j = 0; j < A[0].length; ++j)
            {
                System.out.printf("%d ",this.A[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
