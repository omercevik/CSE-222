/**
 * LinkedList class.
 */
class LinkedList
{
    private Node<Edge> head = null;
    private int size = 0;

    /**
     *
     * @param source Gets source.
     * @param dest Gets destination.
     * @return Returns if it contains, true.
     */
    boolean contains(int source, int dest)
    {
        Node<Edge> obj = new Node<>(new Edge(dest,source));
        Node<Edge> h = head;
        while(h != null)
        {
            if (h.data.equals(obj.data))
            {
                return true;
            }
            h = h.next;
        }
        return false;
    }

    /**
     *
     * @return Returns size of linked list.
     */
    int size()
    {
        return size;
    }

    /**
     *
     * @param item Gets and adds item into linked list.
     */
    void add (Edge item)
    {
        add(size, item);
    }

    /**
     *
     * @param index Gets index.
     * @param item Gets item and adds item to index in linked list.
     */
    private void add(int index, Edge item)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0)
        {
            addFirst(item);
        } else {
            Node<Edge> node = getNode(index-1);
            addAfter(node, item);
        }
    }

    /**
     *
     * @param item Gets item and adds on front.
     */
    private void addFirst (Edge item)
    {
        head = new Node<>(item, head);
        ++size;
    }

    /**
     *
     * @param node Gets node.
     * @param item Gets item and adds on back.
     */
    private void addAfter (Node<Edge> node, Edge item)
    {
        node.next = new Node<>(item, node.next);
        ++size;
    }

    /**
     *
     * @param index Gets index.
     * @return Returns the indexed Edge object.
     */
    Edge get (int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<Edge> node = getNode(index);
        return node.data;
    }

    /**
     *
     * @param index Gets index.
     * @return Returns the indexed Node object.
     */
    private Node<Edge> getNode(int index)
    {
        Node<Edge> node = head;
        for (int i=0; i<index && node != null; ++i)
        {
            node = node.next;
        }
        return node;
    }

    /**
     * Private Node generic inner class.
     * @param <E> Gets parameter as generic E.
     */
    private class Node<E>
    {
        private E data;
        private Node<E> next;

        /**
         * Node class Constructor.
         * @param dataItem Gets data.
         */
        private Node(E dataItem)
        {
            data = dataItem;
            next = null;
        }

        /**
         * Node class Constructor.
         * @param dataItem Gets data.
         * @param nodeRef Gets next node.
         */
        private Node(E dataItem, Node<E> nodeRef)
        {
            data = dataItem;
            next = nodeRef;
        }
    }
}