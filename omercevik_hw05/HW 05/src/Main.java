import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Main class.
 * CSE 222 Data Structures and Algorithms
 * Homework 5
 * 161044004
 * @author Omer CEVIK
 */
public class Main
{
    /**
     * Main method.
     * @param foo Using for image.
     * @throws IOException Throws for reading image.
     */
    public static void main(String[] foo) throws IOException
    {
        BufferedImage img = ImageIO.read(new File(foo[0]));
        int width = img.getWidth();
        int height = img.getHeight();

        ImageValues Array = new ImageValues(height,width);
        PQ PQLEX = new PQ(new LEX(),height,width);
        PQ PQEUC = new PQ(new EUC(),height,width);
        PQ PQBMX = new PQ(new BMX(),height,width);

        for (int i = 0; i < height; ++i)
        {
            for (int j = 0; j < width; ++j)
            {
                Array.setImageArray(i,j,img.getRGB(j,i));
            }
        }

        ProducerConsumer c = new ProducerConsumer(PQLEX, PQEUC, PQBMX);
        Producer p1 = new Producer(c, Array, PQLEX, PQEUC, PQBMX);
        Consumer c2 = new Consumer(c, Array,2);
        Consumer c3 = new Consumer(c, Array,3);
        Consumer c4 = new Consumer(c, Array,4);

        p1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}