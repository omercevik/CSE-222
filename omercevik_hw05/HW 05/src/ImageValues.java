/**
 * ImageValues class to keep pixels of image in 2D array.
 */
class ImageValues
{
    private int[][] ImgArray;
    private int height;
    private int width;

    /**
     * ImageValues constructor.
     * @param height Image's height.
     * @param width Image's width.
     */
    ImageValues(int height, int width)
    {
        ImgArray = new int[height][width];
        this.height = height;
        this.width = width;
    }

    /**
     * Returns pixel of image.
     * @param h Gets height.
     * @param w Gets width.
     * @return Returns pixel of image.
     */
    int getImageArray(int h, int w)
    {
        return ImgArray[h][w];
    }

    /**
     * Sets image with pixel.
     * @param h Height index.
     * @param w Width index.
     * @param data Pixel
     */
    void setImageArray(int h, int w, int data)
    {
        this.ImgArray[h][w] = data;
    }

    /**
     *
     * @return Return height of image.
     */
    int getHeight() {
        return height;
    }

    /**
     *
     * @return Return width of image.
     */
    int getWidth() {
        return width;
    }
}