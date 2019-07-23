import java.util.*;

/**
 * File_Map Class.
 */
public class File_Map implements Map
{
    ArrayList<String> fnames;
    ArrayList<List<Integer>> occurances;
    final static int INITCAP = 100;

    /**
     * File_Map constructor.
     */
    File_Map()
    {
        fnames = new ArrayList<>(INITCAP);
        occurances = new ArrayList<>(INITCAP);
    }

    /**
     *
     * @return Returns fnames list.
     */
    ArrayList<String> getFnames()
    {
        return fnames;
    }

    /**
     *
     * @return Returns occurances list.
     */
    ArrayList<List<Integer>> getOccurances()
    {
        return occurances;
    }

    /**
     *
     * @return Returns size of File_Map.
     */
    @Override
    public int size()
    {
        return fnames.size();
    }

    /**
     *
     * @return Returns if File_Map is empty.
     */
    @Override
    public boolean isEmpty()
    {
        return fnames.isEmpty();
    }

    /**
     *
     * @param key Gets file name.
     * @return Returns is it exist in map.
     */
    @Override
    public boolean containsKey(Object key)
    {
        for (String fname : fnames)
        {
            if (fname.equals(key))
                return true;
        }
        return false;
    }

    /**
     *
     * @param value Gets occurance list.
     * @return Returns is it exist in map.
     */
    @Override
    public boolean containsValue(Object value)
    {
        for (List<Integer> occurance : occurances)
        {
            if (occurance.equals(value))
                return true;
        }
        return false;
    }

    /**
     *
     * @param key Gets file name.
     * @return Returns occurances of file name.
     */
    @Override
    public Object get(Object key)
    {
        return occurances.get(fnames.indexOf(key));
    }

    /**
     *
     * @param key Gets filename.
     * @param value Gets occurances list.
     * @return Pushes file name and occurances to map and returns.
     */
    @Override
    public Object put(Object key, Object value)
    {
        if(containsKey(key))
        {
            List<Integer> removed = occurances.get(fnames.indexOf(key));
            occurances.set(fnames.indexOf(key),(List<Integer>) value);
            return removed;
        }
        else {
            fnames.add((String) key);
            occurances.add((List<Integer>) value);
            return null;
        }
    }

    /**
     *
     * @param key Gets file name to remove.
     * @return Removes and returns filename and occurances.
     */
    @Override
    public Object remove(Object key)
    {
        if(containsKey(key))
        {
            Object retKey = get(key);
            occurances.remove(fnames.indexOf(key));
            fnames.remove(key);
            return retKey;
        }
        return null;
    }

    /**
     *
     * @param m Gets map to put all in file map.
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
     * Clears the file names and occurances.
     */
    @Override
    public void clear()
    {
        fnames.clear();
        occurances.clear();
    }

    /**
     *
     * @return Returns set of file names.
     */
    @Override
    public Set keySet()
    {
        Set<Object> keys = new HashSet<>();
        keys.addAll(fnames);
        return keys;
    }

    /**
     *
     * @return Returns collection of occurances.
     */
    @Override
    public Collection values()
    {
        Collection<Object> values = new ArrayList<>();
        values.addAll(occurances);
        return values;
    }

    /**
     *
     * @return Returns set of all map.
     */
    @Override
    public Set<Entry> entrySet()
    {
        Set<Entry> set = new HashSet<>();

        for (String fname : fnames)
        {
            set.add(new AbstractMap.SimpleImmutableEntry<>(fname,get(fname)));
        }
        return set;
    }
}