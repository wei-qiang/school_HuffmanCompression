package sample;

public class TreeNode {
    private int frequentie;
    private TreeNode child0;
    private TreeNode child1;
    private String value;

    //leaf node constructor
    public TreeNode(int frequentie, String value) {
        this.frequentie = frequentie;
        this.value = value;
    }

    public TreeNode(TreeNode child0, TreeNode child1) {
        this.frequentie = child0.frequentie + child1.frequentie;
        this.child0 = child0;
        this.child1 = child1;
    }

    public TreeNode getChild0() {
        return child0;
    }

    public TreeNode getChild1() {
        return child1;
    }
}
