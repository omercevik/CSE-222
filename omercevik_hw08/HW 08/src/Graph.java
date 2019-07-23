/**
 * Graph class extends AbstractGraph.
 */
class Graph extends AbstractGraph
{
    private LinkedList[] edges;

    /**
     * Graph class Constructor.
     * @param filename Gets filename.
     * @throws Exception Throws for file
     */
    Graph(String filename) throws Exception
    {
        readFile(filename);
        createLinkedList();
        createGraph();
    }

    /**
     * Prints the popularity.
     */
    void printPopular()
    {
        int[] popularity = new int[getNumV()];
        for (LinkedList edge : edges)
        {
            for (int i = 0; i < edge.size(); ++i)
            {
                ++popularity[edge.get(i).getDest()];
            }
        }

        int maxPopular = popularity[0];

        for (int pop : popularity)
        {
            if (maxPopular < pop)
            {
                maxPopular = pop;
            }
        }
        int popularitySize = 0;
        for (int pop : popularity)
        {
            if (maxPopular == pop)
            {
                ++popularitySize;
            }
        }
        System.out.println("Popularity is : ");
        System.out.println(popularitySize);
    }

    /**
     *
     * @param source Gets source.
     * @param dest Gets destination.
     * @return Returns if it is an edge.
     */
    private boolean isEdge(int source, int dest)
    {
        return edges[source].contains(source, dest);
    }

    /**
     *
     * @param edge Gets edge and inserts it to graph.
     */
    private void insert(Edge edge)
    {
        edges[edge.getSource()].add(edge);
    }

    /**
     *
     * @param source Gets source.
     * @param dest Gets destination.
     * @return Returns the edge using source and destination.
     */
    private Edge getEdge(int source, int dest)
    {
        Edge target = new Edge(source, dest);
        for (LinkedList list : edges)
        {
            for (int i = 0; i < list.size(); ++i)
            {
                Edge edge = list.get(source);
                if (edge.equals(target))
                {
                    return edge;
                }
            }
        }
        return target;
    }

    /**
     * Prints current graph.
     */
    private void printGraph()
    {
        for (LinkedList edge : edges)
        {
            for (int j = 0; j < edge.size(); ++j)
            {
                System.out.println("Source : " + edge.get(j).getSource() + " Dest : " + edge.get(j).getDest());
            }
            System.out.println();
        }
    }

    /**
     * Creates the linked list.
     */
    private void createLinkedList()
    {
        edges = new LinkedList[getNumV()];
        for (int i = 0; i < getNumV(); i++)
        {
            edges[i] = new LinkedList();
        }
    }

    /**
     * Creates graph.
     */
    private void createGraph()
    {
        System.out.println("Input Graph :");
        System.out.println("*******************");
        for (String[] strings : GraphArray)
        {
            int indexSource = strings[0].hashCode() % GraphArray.length;
            int indexDest = strings[1].hashCode() % GraphArray.length;
            System.out.println("Source : " + indexSource + " Dest : " + indexDest);
            insert(new Edge(indexDest, indexSource));
        }
        System.out.println();
        boolean flag = true;
        Edge newEdge = null;
        for (int i1 = 0; i1 < edges.length; ++i1)
        {
            LinkedList edge1 = edges[i1];
            for (int i = 0; i < edge1.size() && flag; ++i)
            {

                for (int i2 = 0; i2 < edges.length && flag; ++i2)
                {
                    LinkedList edge2 = edges[i2];
                    for (int j = 0; j < edge2.size() && flag; ++j)
                    {
                        if (edge1.get(i).getDest() == edge2.get(j).getSource() && edge1.get(i).getSource() != edge2.get(j).getDest())
                        {
                            newEdge = new Edge(edge2.get(j).getDest(), edge1.get(i).getSource());
                            flag = false;
                        }
                    }
                }
            }
            if (newEdge != null)
            {
                if (!isEdge(newEdge.getSource(), newEdge.getDest()))
                {
                    insert(newEdge);
                    i1 = 0;
                    flag = true;
                    newEdge = null;
                }
            }
        }
        System.out.println("Relinked Graph :");
        System.out.println("*******************");
        printGraph();
        System.out.println("*******************");
    }
}