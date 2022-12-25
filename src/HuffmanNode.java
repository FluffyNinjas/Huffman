public class HuffmanNode implements Comparable
{
    public String letter;
    public Double frequency;
    public HuffmanNode left, right;
    public HuffmanNode(String letter, Double frequency){
        this.letter = letter;
        this.frequency = frequency;
        this.left = null;
        this.right = null;

    }
    public HuffmanNode(HuffmanNode left, HuffmanNode right){
        this.letter = left.letter + right.letter;
        this.frequency = (right.frequency + left.frequency);

    }
    public int compareTo(Object o){
        HuffmanNode huff = (HuffmanNode) o;

        return this.frequency.compareTo(huff.frequency);
    }
    public String toString(){

        return "<"+letter+", "+frequency+">";
    }
}
