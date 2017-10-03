package sample;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextDecrompressor {
    private StringBuilder bitString = null;
    private ObjectInputStream objectInputStream = null;
    private FileInputStream fileInputStream = null;

    StringBuilder text = new StringBuilder();
    TreeNode currentNode = null;
    boolean valueFound = false;

    public TextDecrompressor() {
        BitReader bitReader = new BitReader();
        bitString = bitReader.readBits("test");
    }

    public StringBuilder getBitString() {
        return bitString;
    }

    /**
     * deze methode haalt de treenode op die geserialiseerd is
     * @param fileName
     * @return
     */
    public TreeNode treeNodeReader(String fileName) {
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return (TreeNode) objectInputStream.readObject();
        } catch (Exception e) {
            Logger.getLogger(TextDecrompressor.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    Logger.getLogger(TextDecrompressor.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    Logger.getLogger(TextDecrompressor.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return null;
    }

    /**
     * deze methode vertaalt de bits om naar characters volgens de tree
     * @param bitString
     * @param rootnode
     * @return
     */
    public String decompressBits(StringBuilder bitString, TreeNode rootnode) {
        ArrayList<String> bits = new ArrayList<>(Arrays.asList(bitString.toString().split("")));
        currentNode = rootnode;

        while (!bits.isEmpty()) {
            if (bits.get(0).equals("0")) {
                checkChild(false, rootnode);
            } else {
                checkChild(true, rootnode);
            }
            bits.remove(0);
        }
        while (!valueFound) {
            checkChild(false, rootnode);
        }

        return text.toString().substring(0, rootnode.getFrequentie());

    }

    private void checkChild(boolean bit, TreeNode rootNode) {
        if (bit) {
            if (currentNode.getChild1().getValue() != null) {
                text.append(currentNode.getChild1().getValue());
                currentNode = rootNode;
                valueFound = true;
            } else {
                currentNode = currentNode.getChild1();
                valueFound = false;
            }
        } else {
            if (currentNode.getChild0().getValue() != null) {
                text.append(currentNode.getChild0().getValue());
                currentNode = rootNode;
                valueFound = true;
            } else {
                currentNode = currentNode.getChild0();
                valueFound = false;
            }
        }
    }
}
