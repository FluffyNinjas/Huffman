import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HuffmanConverter {
    // The # of chars in the ASCII table dictates
// the size of the count[] & code[] arrays.
    public static final int NUMBER_OF_CHARACTERS = 256;
    // the contents of our message...
    private String contents;
    // the tree created from the msg
    private HuffmanTree huffmanTree;
    // tracks how often each character occurs
    private int count[];
    // the huffman code for each character
    private String code[];
    // stores the # of unique chars in contents
    private int uniqueChars = 0; //(optional)
    /** Constructor taking input String to be converted */
    public HuffmanConverter(String input)
    {
        this.contents = input;
        this.count = new int[NUMBER_OF_CHARACTERS];
        this.code = new String[NUMBER_OF_CHARACTERS];
    }
    /**
     * Records the frequencies that each character of our
     * message occurs...
     * I.e., we use 'contents' to fill up the count[] list...
     */
    public void recordFrequencies(){
        char[] x = contents.toCharArray();
        for(char y: x){
            count[(int)y] += 1;
        }
    }

    /**
     * Converts our frequency list into a Huffman Tree. We do this by
     * taking our count[] list of frequencies, and creating a binary
     * heap in a manner similar to how a heap was made in HuffmanTree's
     * fileToHeap method. Then, we print the heap, and make a call to
     * HuffmanTree.heapToTree() method to get our much desired
     * HuffmanTree object, which we store as huffmanTree.
     */
    public void frequenciesToTree(){
         BinaryHeap x = new BinaryHeap();
         for(int i = 0; i<count.length;i++){
             if(count[i] == ' '){
                 continue;
             }
             else{
                 HuffmanNode y = new HuffmanNode(" "+(char)(i),(double)count[i]);
                 x.insert(y);
             }
         }
         x.printHeap();
         huffmanTree = HuffmanTree.createFromHeap(x);
    }



    /**
     * Iterates over the huffmanTree to get the code for each letter.
     * The code for letter i gets stored as code[i]... This method
     * behaves similarly to HuffmanTree's printLegend() method...
     * Warning: Don't forget to initialize each code[i] to ""
     * BEFORE calling the recursive version of treeToCode...
     */
    public void treeToCode(){
        treeToCode(huffmanTree.root,"");
    }




    /*
     * A private method to iterate over a HuffmanNode t using s, which
     * contains what we know of the HuffmanCode up to node t. This is
     * called by treeToCode(), and resembles the recursive printLegend
     * method in the HuffmanTree class. Note that when t is a leaf node,
     * t's letter tells us which index i to access in code[], and tells
     * us what to set code[i] to...
     */
    private void treeToCode(HuffmanNode t, String s){
        if(t.letter.length()>1){
            treeToCode(t.left,s+"1");
            treeToCode(t.right,s+"0");
        }
        else if( t.letter.length() == 1){
            code[(int)t.letter.charAt(0)] = "";
            code[(int)t.letter.charAt(0)] = s;
        }
    }






    /**
     * Using the message stored in contents, and the huffman conversions
     * stored in code[], we create the Huffman encoding for our message
     * (a String of 0's and 1's), and return it...
     */
    public String encodeMessage(){
        String x = "";
        char[] y = contents.toCharArray();
        for(int i = 0;i<y.length;i++){
            x+=code[(int)y[i]];
        }
        return x;
    }



    /**
     * Reads in the contents of the file named filename and returns
     * it as a String. The main method calls this method on args[0]...
     */
    public static String readContents(String filename) throws Exception{
        String x = "";
        try{
            File f  =new File(filename);
            Scanner input = new Scanner(f);
            while(input.hasNextLine()){
                x+= input.nextLine() + "\n";

            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return x;
    }


    /**
     * Using the encoded String argument, and the huffman codings,
     * re-create the original message from our
     * huffman encoding and return it...
     */
    public String decodeMessage(String encodedStr) {
        StringBuilder x = new StringBuilder();
        HuffmanNode y = huffmanTree.root;
        for (int i = 0; i < encodedStr.length(); i++) {
            if (encodedStr.charAt(i) == '0') {
                y = y.right;
            } else {
                y = y.left;
            }
            if(y.left == null && y.right == null) {
                x.append(y.letter);
                y = huffmanTree.root;
            }


        }
        return x.toString();
    }

/**
 * Uses args[0] as the filename, and reads in its contents. Then
 * instantiates a HuffmanConverter object, using its methods to
 * obtain our results and print the necessary output. Finally,
 * decode the message and compare it to the input file.<p>
 * NOTE: Example method provided below...

 **/
 public static void main(String args[]) throws Exception {
 //call all your methods from here

     String p = readContents(args[0]);

     HuffmanConverter y= new HuffmanConverter(p);

     y.recordFrequencies();

     y.frequenciesToTree();

     y.treeToCode();
     y.huffmanTree.printLegend();

     String a = y.encodeMessage();
     String b = y.decodeMessage(a);
     System.out.println(a);
     System.out.println(b);
     System.out.println(y.contents.length()*8);
     System.out.println(a.length());
     System.out.println(b);


 }

 }
