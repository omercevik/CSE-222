/**
 * BMX class to implement BMX.
 */
public class BMX implements Comparator
{
    /**
     * Returns 1 bit.
     * @param n Gets color.
     * @param k Gets how many shifts.
     * @return  Returns 1 bit.
     */
    private int getBit(int n, int k)
    {
        return (n >> k) & 1;
    }

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
        int RGB1 = 0, RGB2 = 0;

        for (int i = 7; i > 0; --i)
        {
            RGB1 = getBit(r1,i);
            RGB1 = RGB1 << 1;
            RGB1 |= getBit(g1,i);
            RGB1 = RGB1 << 1;
            RGB1 |= getBit(b1,i);
            RGB1 = RGB1 << 1;

            RGB2 = getBit(r2,i);
            RGB2 = RGB2 << 1;
            RGB2 |= getBit(g2,i);
            RGB2 = RGB2 << 1;
            RGB2 |= getBit(b2,i);
            RGB2 = RGB2 << 1;
        }
        return RGB1 > RGB2;
    }
}