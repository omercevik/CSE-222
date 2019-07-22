/**
 * Consumer class extends Thread to solve Producer - Consumer Problem.
 */
class Consumer extends Thread
{
    private ProducerConsumer poc;
    private int number;
    private ImageValues ImgObj;

    /**
     * Consumer constructor.
     * @param p Gets ProducerConsumer relation object.
     * @param i Gets image array.
     * @param number Gets thread number.
     */
    Consumer(ProducerConsumer p, ImageValues i, int number)
    {
        ImgObj = i;
        poc = p;
        this.number = number;
    }

    /**
     * Run method to use threads.
     */
    @Override
    public void run()
    {
        for (int i = 0; i < ImgObj.getHeight(); ++i)
        {
            for (int j = 0; j < ImgObj.getWidth(); ++j)
            {
                poc.get(ImgObj.getImageArray(i,j),number);
            }
        }
    }
}