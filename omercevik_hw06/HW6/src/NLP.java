import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * NLP class.
 */
class NLP
{
    private Word_Map wmap;

    NLP()
    {
        wmap = new Word_Map();
    }
    private String[][] bigrams;
    private int fileSizes = 0;

    /**
     * Reads dataset into Word_Map object.
     * @param dir Gets directory of dataset.
     * @throws Exception Throws file exception.
     */
    void readDataset(String dir) throws Exception
    {
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        bigrams = new String[listOfFiles.length][];
        for (File file : listOfFiles)
        {
            if (file.isFile())
            {
                BufferedReader br = new BufferedReader(new FileReader(new File(dir+"/"+file.getName())));
                StringBuffer word = new StringBuffer();
                String line;
                while((line = br.readLine()) != null)
                {
                    word.append(line.replaceAll("\\p{Punct}", "").replaceAll("\\s+", " ").trim()).append(" ");
                }
                String str = String.valueOf(word);
                bigrams[fileSizes] = str.split(" ");
                ArrayList<Integer> occ = new ArrayList<>();

                for (int i = 0; i < bigrams[fileSizes].length; i++)
                {
                    String bigram = bigrams[fileSizes][i];
                    File_Map fmp = (File_Map) wmap.get(bigram);
                    if (fmp == null)
                    {
                        fmp = new File_Map();
                    }
                    for (int j = 0; j < bigrams[fileSizes].length; ++j)
                    {
                        if (bigram.equals(bigrams[fileSizes][j])) {
                            occ.add(j);
                        }
                    }
                    fmp.put(file.getName(), occ);
                    wmap.put(bigram, fmp);
                    occ = new ArrayList<>();
                }
                ++fileSizes;
            }
        }
    }

    /**
     *
     * @param word Gets word.
     * @return Returns biagram list of words.
     */
    List<String> bigrams(String word)
    {
        List<String> bigrs = new ArrayList<>();

        if (wmap.containsKey(word))
        {
            for (String[] bigram : bigrams)
            {
                for (int j = 0; j < bigram.length; ++j)
                {
                    if (bigram[j].equals(word) && j + 1 != bigram.length)
                    {
                        if (!bigrs.contains(word + " " + bigram[j + 1]))
                            bigrs.add(word + " " + bigram[j + 1]);
                    }
                }
            }
        }
        return bigrs;
    }

    /**
     *
     * @param word Gets word.
     * @param fileName Gets file name.
     * @return Returns TFIDF evaluation.
     */
    float tfIDF(String word, String fileName)
    {
        float totalFiles = 0f, numberDocs = 0f;

        for (int i = 0; i < wmap.size(); i++)
        {
            if (wmap.getTable(i) != null)
            {
                for (int j = 0; j < ((File_Map)wmap.getTable(i).getFileHash()).size(); j++)
                {
                    File_Map fmp = (File_Map)wmap.getTable(i).getFileHash();
                    if (fmp.getFnames().get(j).equals(fileName))
                    {
                        totalFiles = totalFiles > fmp.getOccurances().get(j).get(fmp.getOccurances().get(j).size()-1) ? totalFiles : fmp.getOccurances().get(j).get(fmp.getOccurances().get(j).size()-1);
                    }
                }
            }
        }

        float TF = ((ArrayList)((File_Map)wmap.get(word)).get(fileName)).size()/totalFiles;

        for (int i = 0; i < wmap.size(); i++)
        {
            if (wmap.getTable(i) != null)
            {
                if (wmap.getTable(i).getWord().equals(word))
                {
                    numberDocs += ((File_Map)wmap.getTable(i).getFileHash()).size();
                    //break;
                }
            }
        }
        float IDF = (float)Math.log(fileSizes / numberDocs);
        float TFIDF = TF*IDF;
        return TFIDF;
    }

    /**
     * Prints Word_Map using iterator.
     */
    void printWordMap()
    {
        Iterator it = wmap.iterator();
        while (it.hasNext())
        {
            Word_Map.Node w = (Word_Map.Node) it.next();

            System.out.println("Word : " + w.getWord());
            System.out.println();
            System.out.println("File_HashMap_FileNames : ");
            System.out.println();
            System.out.println(((File_Map)w.getFileHash()).getFnames());
            System.out.println();
            System.out.println("File_HashMap_Occurances : ");
            System.out.println();
            System.out.println(((File_Map)w.getFileHash()).getOccurances());
            System.out.println();
            System.out.println();
        }
    }
}