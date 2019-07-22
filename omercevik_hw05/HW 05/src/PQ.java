/**
 * Priority Queue class.
 */
class PQ
{
    private Heap BinHeap;
    private int front;
    private int rear;
    private int capacity;
    private int count;

    /**
     * Priority Queue constructor.
     * @param C Gets comparator.
     * @param height Gets height of image.
     * @param width Gets width of image.
     */
    PQ(Comparator C, int height, int width)
    {
        this.BinHeap = new Heap(height*width,C);
        this.capacity = height*width;
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    /**
     *
     * @return Returns Heap.
     */
    Heap getBinHeap()
    {
       return BinHeap;
    }

    /**
     * Deletes from Queue.
     */
    void dequeue()
    {
        if (isEmpty())
        {
            System.out.println("UnderFlow\nProgram Terminated");
            System.exit(1);
        }

        front = (front + 1) % capacity;
        --count;
        BinHeap.pop();
    }

    /**
     * Inserts into Queue.
     * @param item Insert element.
     */
    void enqueue(int item)
    {
        if (isFull())
        {
            System.out.println("OverFlow\nProgram Terminated");
            System.exit(1);
        }

        BinHeap.insert(item);

        rear = (rear + 1) % capacity;
        ++count;
    }

    /**
     *
     * @return Returns size of Queue.
     */
    private int size()
    {
        return count;
    }

    /**
     *
     * @return Returns if Queue is empty.
     */
    Boolean isEmpty()
    {
        return (size() == 0);
    }

    /**
     *
     * @return Returns if Queue is full.
     */
    private Boolean isFull()
    {
        return (size() == capacity);
    }
}