package sample;

import java.io.Serializable;

public class TreeNode implements Serializable {
    private int frequentie;
    private TreeNode parent;
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
        child0.setParent(this);
        child1.setParent(this);
    }

    public TreeNode getChild0() {
        return child0;
    }

    public TreeNode getChild1() {
        return child1;
    }

    public int getFrequentie() {
        return frequentie;
    }

    public String getValue() {
        return value;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }

    public StringBuilder toBit() {
        if (value != null) {
            StringBuilder stringBuilder = new StringBuilder();
            TreeNode currentNode = this;
            while (currentNode.getParent() != null) {
                if (currentNode.getParent().getChild0() == currentNode) {
                    stringBuilder.insert(0, "0");
                } else {
                    stringBuilder.insert(0, "1");
                }
                currentNode = currentNode.getParent();
            }
            return stringBuilder;
        }
        return null;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "frequentie=" + frequentie +
                ", child0=" + child0 +
                ", child1=" + child1 +
                ", value='" + value + '\'' +
                '}';
    }
}
