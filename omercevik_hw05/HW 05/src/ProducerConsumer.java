/**
 * ProducerConsumer class to solve Producer-Consumer Problem using threads.
 */
class ProducerConsumer {

    private boolean available = false;
    private PQ LEX;
    private PQ BMX;
    private PQ EUC;

    /**
     * ProducerConsumer constructor.
     * @param LEX Gets LEX Queue.
     * @param BMX Gets BMX Queue.
     * @param EUC Gets EUC Queue.
     */
    ProducerConsumer(PQ LEX, PQ BMX, PQ EUC)
    {
        this.LEX = LEX;
        this.BMX = BMX;
        this.EUC = EUC;
    }

    /**
     * Synchronized get method to synchronize threads.
     * @param value Gets pixel of image.
     * @param number Gets thread number.
     */
    synchronized void get(int value, int number)
    {
        while (!available)
        {
            try
            {
                wait();
            }
            catch (InterruptedException ignored) {}
        }


        if (number == 2)
        {
            if (LEX.isEmpty())
            {
                try
                {
                    wait();
                } catch (InterruptedException ignored) {}
            }
            else {
                available = true;
                System.out.printf("Thread%d-", number);
                System.out.println("PQLEX: [" + LEX.getBinHeap().getR(value) + "," + LEX.getBinHeap().getG(value)
                        + "," + LEX.getBinHeap().getB(value) + "]");
                LEX.dequeue();
            }
        }
        else if (number == 3)
        {
            if (EUC.isEmpty())
            {
                try
                {
                    wait();
                } catch (InterruptedException ignored) {}
            }
            else {
                available = true;
                System.out.printf("Thread%d-", number);
                System.out.println("PQEUC: [" + EUC.getBinHeap().getR(value) + "," + EUC.getBinHeap().getG(value)
                        + "," + EUC.getBinHeap().getB(value) + "]");
                EUC.dequeue();
            }
        }
        else if (number == 4)
        {
            if (BMX.isEmpty())
            {
                try
                {
                    wait();
                } catch (InterruptedException ignored) {}
            }
            else {
                available = true;
                System.out.printf("Thread%d-", number);
                System.out.println("PQBMX: [" + BMX.getBinHeap().getR(value) + "," + BMX.getBinHeap().getG(value)
                        + "," + BMX.getBinHeap().getB(value) + "]");
                BMX.dequeue();
            }
        }
    }

    /**
     * Synchronized get method to synchronize threads.
     * @param value Gets Counter of first 100 pixels.
     */
    synchronized void put(int value)
    {
        if (value == 100)
        {
            available = true;
            notifyAll();
        }
        else if (value > 100)
        {
            available = true;
            notifyAll();
        }
        else
            available = false;
    }
}