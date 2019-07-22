/**
 * Producer class extends Thread to solve Producer - Consumer Problem.
 */
class Producer extends Thread
{
    private PQ PQLEX;
    private PQ PQEUC;
    private PQ PQBMX;
    private ImageValues ImgObj;
    private ProducerConsumer proCon;

    /**
     * Constructor of Producer.
     * @param c Gets ProducerConsumer relation object.
     * @param Img Gets image array.
     * @param PQLEX Gets LEX Queue.
     * @param PQEUC Gets EUC Queue.
     * @param PQBMX Gets BMX Queue.
     */
    Producer(ProducerConsumer c,ImageValues Img, PQ PQLEX, PQ PQEUC, PQ PQBMX)
    {
        this.proCon = c;
        this.ImgObj = Img;
        this.PQLEX = PQLEX;
        this.PQEUC = PQEUC;
        this.PQBMX = PQBMX;
    }

    /**
     * Run method to use threads.
     */
    @Override
    public void run()
    {
        int Counter = 0;
        for (int i = 0; i < ImgObj.getHeight(); ++i)
        {
            for (int j = 0; j < ImgObj.getWidth(); ++j)
            {
                ++Counter;
                int pixel = ImgObj.getImageArray(i,j);
                PQLEX.enqueue(pixel);
                PQEUC.enqueue(pixel);
                PQBMX.enqueue(pixel);
                System.out.println("Thread 1: [" + PQLEX.getBinHeap().getR(pixel) + ", "
                        + PQLEX.getBinHeap().getG(pixel) + ", " + PQLEX.getBinHeap().getB(pixel) + "]");
                proCon.put(Counter);
            }
        }
    }
}

