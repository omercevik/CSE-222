/**
 * Edge class.
 */
class Edge
{
    private int dest;
    private int source;

    /**
     * Edge class Constructor.
     * @param dest Gets destination.
     * @param source Gets source.
     */
    Edge(int dest, int source)
    {
        this.dest = dest;
        this.source = source;
    }

    /**
     *
     * @return Returns destination.
     */
    int getDest()
    {
        return dest;
    }

    /**
     *
     * @return Returns source.
     */
    int getSource()
    {
        return source;
    }

    /**
     *
     * @param o Gets Edge object.
     * @return Returns if its equals to current object.
     */
    boolean equals(Edge o)
    {
        return o.getDest() == this.getDest() && o.getSource() == this.getSource();
    }
}