/**
 * EUC class to implement BMX.
 */
public class EUC implements Comparator
{
    /**
     *
     * @param r1 First color's red.
     * @param g1 First color's green.
     * @param b1 First color's blue.
     * @param r2 Second color's red.
     * @param g2 Second color's green.
     * @param b2 Second color's blue.
     * @return Returns RGB1 > RGB2.
     */
    @Override
    public boolean compare(int r1, int g1, int b1, int r2, int g2, int b2)
    {
        return Math.sqrt(r1*r1 + g1*g1 + b1*b1) > Math.sqrt(r2*r2 + g2*g2 + b2*b2);
    }
}