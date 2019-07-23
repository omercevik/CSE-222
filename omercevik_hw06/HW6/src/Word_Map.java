import java.util.*;

/**
 * Word_Map class.
 */
public class Word_Map implements Map, Iterable
{
    private final static int INITCAP = 100;
    private int CURRCAP = INITCAP;
    private final static float LOADFACT = 0.75f;
    private Node[] table;
    private Node Head;
    private Node current;
    private int tableSize = 0;

    /**
     * Word_Map constructor.
     */
    Word_Map()
    {
        this.table = new Node[INITCAP];
    }

    /**
     *
     * @return Returns iterator of Word_Map.
     */
    @Override
    public Iterator iterator()
    {
        current = Head;

        Iterator<Node> iterator = new Iterator<>()
        {
            @Override
            public boolean hasNext()
            {
                return current != null;
            }

            @Override
            public Node next()
            {
                if (hasNext())
                {
                    Node temp = current;
                    current = current.next;
                    return temp;
                }
                else
                {
                    throw new NoSuchElementException();
                }
            }
        };
        return iterator;
    }

    /**
     * Node class keeps File_Map and file name.
     */
    static class Node
    {
        private Object word;
        private Object fileHash;
        private Node next;

        /**
         * Node constructor.
         * @param key Gets file name.
         * @param value Gets File_Map object.
         */
        Node(Object key, Object value)
        {
            this.word = key;
            this.fileHash = value;
        }

        /**
         *
         * @return Returns file name.
         */
        Object getWord()
        {
            return word;
        }

        /**
         *
         * @return Returns File_Map object.
         */
        Object getFileHash()
        {
            return fileHash;
        }
    }

    /**
     * Returns table array.
     * @param i Gets index.
     * @return Returns table's i indexed object.
     */
    Node getTable(int i)
    {
        return table[i];
    }

    /**
     *
     * @return Returns size of table.
     */
    @Override
    public int size()
    {
        return tableSize;
    }

    /**
     *
     * @return Returns if table is empty.
     */
    @Override
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     *
     * @param key Gets file name.
     * @return Returns true if it is exist in map.
     */
    @Override
    public boolean containsKey(Object key)
    {
        for (Node node : table)
        {
            if (node != null)
            {
                if (node.word.equals(key))
                    return true;
            }
        }
        return false;
    }

    /**
     *
     * @param value Gets File_Map object.
     * @return Returns true if it is exist in map.
     */
    @Override
    public boolean containsValue(Object value)
    {
        for (Node node : table)
        {
            if (node != null)
            {
                if (node.fileHash.equals(value))
                    return true;
            }
        }
        return false;
    }

    /**
     *
     * @param key Gets file name.
     * @return Returns hash index of file name.
     */
    private int findIndex(Object key)
    {
        int index = key.hashCode() % CURRCAP;

        if (index < 0)
        {
            index += CURRCAP;
        }

        while((table[index] != null) && (!key.equals(table[index].word)))
        {
            ++index;
            if (index >= CURRCAP)
            {
                index = 0;
            }
        }
        return index;
    }

    /**
     *
     * @param key Gets file name.
     * @return Returns File_Map object.
     */
    @Override
    public Object get(Object key)
    {
        if (key == null)
        {
            return null;
        }

        int index = findIndex(key);

        return table[index] != null ? table[index].fileHash : null;
    }

    /**
     *
     * @param key Gets file name.
     * @param value Gets File_Map object.
     * @return Returns null or old value of File_Map.
     */
    @Override
    public Object put(Object key, Object value)
    {
        Node item = new Node(key, value);
        item.next = Head;
        Head = item;

        int index = findIndex(key);
        if (table[index] == null)
        {
            table[index] = new Node(key,value);

            ++tableSize;
            double loadFactor = (double)(tableSize)/CURRCAP;
            if (loadFactor > LOADFACT)
            {
                rehash();
            }
            return null;
        }

        Object oldVal = table[index].fileHash;
        table[index].fileHash = value;
        return oldVal;
    }

    /**
     * Reallocates table's size.
     */
    private void rehash()
    {
        Node[] oldTable = table;

        table = new Node[2 * CURRCAP + 1];
        CURRCAP = 2 * CURRCAP + 1;
        tableSize = 0;

        for (Node node : oldTable)
        {
            if (node != null)
            {
                put(node.word, node.fileHash);
            }
        }
    }

    /**
     * (Not implemented.)
     * @param key Gets file name. (Not implemented.)
     * @return Returns removed File_Map. (Not implemented.)
     */
    @Override
    public Object remove(Object key)
    {
        return null;
    }

    /**
     *
     * @param m Gets Map to put all in Word_Map.
     */
    @Override
    public void putAll(Map m)
    {
        for (Object o : m.keySet())
        {
            put(o,m.get(o));
        }
    }

    /**
     * Refreshes Word_Map.
     */
    @Override
    public void clear()
    {
        this.table = new Node[INITCAP];
        this.CURRCAP = INITCAP;
        this.tableSize = 0;
    }

    /**
     *
     * @return Returns set of words.
     */
    @Override
    public Set keySet()
    {
        Set<Object> keys = new HashSet<>();
        for (Node node : table)
        {
            if (node != null)
                keys.add(node.word);
        }
        return keys;
    }

    /**
     *
     * @return Returns collection of File_Maps.
     */
    @Override
    public Collection values()
    {
        Collection<Object> values = new ArrayList<>();
        for (Node node : table)
        {
            if (node != null)
                values.add(node.fileHash);
        }
        return values;
    }

    /**
     * (Not implemented.)
     * @return Returns set of map. (Not implemented.)
     */
    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}