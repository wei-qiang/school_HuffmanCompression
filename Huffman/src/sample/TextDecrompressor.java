package sample;

import java.io.*;
import java.util.*;

public class TextDecrompressor {
    private StringBuilder bitString = null;
    private FileInputStream fileInputStream = null;
    private ObjectInputStream objectInputStream = null;
    private TreeNode rootNode = null;

    StringBuilder text = new StringBuilder();
    TreeNode currentNode = null;
    boolean valueFound = false;

    public TextDecrompressor() {
        BitReader bitReader = new BitReader();
        bitString = bitReader.readBits("test");
        treeNodeReader("testTree.ser");
    }

    private void treeNodeReader(String fileName) {
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            rootNode = (TreeNode) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String decompressBits() {
        ArrayList<String> bits = new ArrayList<>(Arrays.asList(bitString.toString().split("")));
        currentNode = rootNode;

        while (bits.size() > 0) {
            if (bits.get(0).equals("0")) {
                checkChild(false);
            } else {
                checkChild(true);
            }
            bits.remove(0);
        }
        while (!valueFound) {
            checkChild(false);
        }

        return text.toString().substring(0,rootNode.getFrequentie());

    }

    private void checkChild(boolean bit) {
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
