import java.io.*;
import java.util.PriorityQueue;

public class Huffman
{
    //Final Variables
    private static final int R = 256;
    private static final int huffmanTreeLength = 512;
    private static final int EOF = -1;

    //Compress file
    public static void compress(String filename) throws IOException {

        //Initialize forest of all ASCII chars in array form
        BinaryTree[] forest = new BinaryTree[R];
        for(int i  = 0; i < R; i++)
        {
            forest[i] = new BinaryTree<Character>((char)i);
        }

        //First pass iteration over file to determine character frequency and construct huffman tree
            //Establish file input stream
        StringBuilder path = new StringBuilder(System.getProperty("user.dir"));
        path.append("\\src\\");
        path.append(filename);
        FileInputStream fs = null;
        try
        { fs = new FileInputStream(new File(path.toString())); }
        catch (FileNotFoundException e)
        { System.out.println(e); }

            //Iterate over file to collect char frequencies
        int ch;
        while((ch = fs.read()) != EOF)
        {
            forest[ch].incrementWeight();
        }

            //Move array to priority queue
        PriorityQueue<BinaryTree> pq = new PriorityQueue<>(R, new BinaryTreeWeightComparator());
        for(int i  = 0; i < R; i++)
        {
            pq.add(forest[i]);
        }

            //Build huffman tree
        while(pq.size() > 1)
        {
            pq.add(new BinaryTree(pq.poll(), pq.poll()));
        }

        //Write huffman tree into file
            //Establish file output stream
        StringBuilder outPath = new StringBuilder(System.getProperty("user.dir"));
        outPath.append("\\src\\" + filename.substring(0, filename.indexOf(".")) + ".huf");
        FileOutputStream out = null;
        try
        { out = new FileOutputStream(new File(outPath.toString())); }
        catch (FileNotFoundException e)
        { System.out.println(e); }

            //Write inorder Huffman Tree with flag bits for
        BinaryTree node = pq.peek();
        node.traversePreOrder();

        //Second pass over file to encode content

        out.close();
    }

    //Expand File
    public static void expand()
    {

    }

    public static void main(String[] args) throws IOException {
        if      (args[0].equals("-")) compress(args[1]);
        else if (args[0].equals("+")) expand();
        else throw new RuntimeException("Illegal command line argument");
    }
}
