/**
 * Binary Tree Heap class.
 */
class Heap
{
    private int[] Heap;
    private int size;
    private Comparator Com;

    /**
     * Heap constructor.
     * @param maxsize Gets heap's maxsize.
     * @param C Gets comparator.
     */
    Heap(int maxsize, Comparator C)
    {
        this.size = 0;
        this.Com = C;
        this.Heap = new int[maxsize + 1];
        this.Heap[0] = Byte.MAX_VALUE;
    }

    /**
     * Inserts to binary heap tree.
     * @param element Insert element.
     */
    void insert(int element)
    {
        Heap[++size] = element;
        int current = size;
        while (Com.compare(getR(Heap[current]),getG(Heap[current]),getB(Heap[current]),
                getR(Heap[getParent(current)]),getG(Heap[getParent(current)]),getB(Heap[getParent(current)])))
        {
            swap(current, getParent(current));
            current = getParent(current);
        }
    }

    /**
     *
     * @param pos Gets parent position.
     * @return Returns parent.
     */
    private int getParent(int pos)
    {
        return pos / 2;
    }

    /**
     *
     * @param pos Gets left child position.
     * @return Returns left child.
     */
    private int getLeft(int pos)
    {
        return (2 * pos);
    }

    /**
     *
     * @param pos Gets right child position.
     * @return Returns right child.
     */
    private int getRight(int pos)
    {
        return (2 * pos) + 1;
    }

    /**
     * Swaps child and parent.
     * @param end Gets child.
     * @param begin Gets parent.
     */
    private void swap(int end, int begin)
    {
        int tmp = Heap[end];
        Heap[end] = Heap[begin];
        Heap[begin] = tmp;
    }

    /**
     * Inserted item to max position.
     * @param pos Checks which position it needs to be.
     */
    private void HeapToMax(int pos)
    {
        if (isLeaf(pos))
            return;
        if (Com.compare(getR(Heap[getLeft(pos)]),getG(Heap[getLeft(pos)]),getB(Heap[getLeft(pos)]),
                        getR(Heap[pos]),getG(Heap[pos]),getB(Heap[pos]))
                                            ||
            Com.compare(getR(Heap[getRight(pos)]),getG(Heap[getRight(pos)]),getB(Heap[getRight(pos)]),
                    getR(Heap[pos]),getG(Heap[pos]),getB(Heap[pos])))
        {
            if (Com.compare(getR(Heap[getLeft(pos)]),getG(Heap[getLeft(pos)]),getB(Heap[getLeft(pos)]),
                    getR(Heap[getRight(pos)]),getG(Heap[getRight(pos)]),getB(Heap[getRight(pos)])))
            {
                swap(pos, getLeft(pos));
                HeapToMax(getLeft(pos));
            }
            else {
                swap(pos, getRight(pos));
                HeapToMax(getRight(pos));
            }
        }
    }

    /**
     * Checks leaves.
     * @param pos Gets position of leaf.
     * @return Returns if leaf.
     */
    private boolean isLeaf(int pos)
    {
        return (pos >= (size / 2)) && (pos <= size);
    }

    /**
     * Returns red color.
     * @param p Gets pixel.
     * @return Returns red color.
     */
    int getR(int p)
    {
        return (p>>16) & 0xff;
    }

    /**
     * Returns green color.
     * @param p Gets pixel.
     * @return Returns green color.
     */
    int getG(int p)
    {
        return (p>>8) & 0xff;
    }

    /**
     * Returns blue color.
     * @param p Gets pixel.
     * @return Returns blue color.
     */
    int getB(int p)
    {
        return p & 0xff;
    }

    /**
     * Deletes from heap.
     */
    void pop()
    {
        Heap[1] = Heap[size--];
        HeapToMax(1);
    }
}