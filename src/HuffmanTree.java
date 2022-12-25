import java.util.Scanner;

public class HuffmanTree
{
    HuffmanNode root;
    public HuffmanTree(HuffmanNode huff){
        this.root = huff;
    }
    public void printLegend(){
        printLegend(root,"");


    }
    private void printLegend(HuffmanNode t, String s){

        if(t.letter.length() > 1){

            printLegend(t.left,s + "0");
            printLegend(t.right,s + "1");
        }
        if(t.letter.length() == 1){
            System.out.println(t.letter+"="+s);
        }


    }
    public static BinaryHeap legendToHeap(String legend){
        String[] character = legend.split(" ");
        HuffmanNode[] x = new HuffmanNode[(character.length)/2];
        for(int i = 0; i<character.length; i+=2){
            String letter = character[i];
            Double frequency = Double.parseDouble(character[i+1]);
            HuffmanNode element = new HuffmanNode(letter, frequency);
            x[i/2] = element;
        }
        BinaryHeap jc = new BinaryHeap(x);

        return jc;
    }
    public static HuffmanTree createFromHeap(BinaryHeap b){
        while(b.getSize()!=1){
            HuffmanNode x = (HuffmanNode)b.deleteMin();
            HuffmanNode y = (HuffmanNode)b.deleteMin();
            HuffmanNode z =new HuffmanNode(x,y);
            z.left = x;
            z.right = y;
            b.insert(z);
        }
        HuffmanTree p = new HuffmanTree((HuffmanNode)b.findMin());
        return p;
    }
    public static void main(String[] args){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter legend");
        String legend = myObj.nextLine();
        BinaryHeap bheap = legendToHeap(legend);
        bheap.printHeap();
        HuffmanTree htree = createFromHeap(bheap);

        htree.printLegend();


    }
}