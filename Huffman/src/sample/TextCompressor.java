package sample;

import sun.reflect.generics.tree.Tree;

import java.io.IOException;
import java.util.*;

public class TextCompressor {
    private ArrayList<String> characterList = new ArrayList<>();
    private HashMap<String, Integer> map = new HashMap<>();
    private PriorityQueue<TreeNode> queue = new PriorityQueue(1000, new FrequentieComparator());
    private TreeNode rootNode = null;
    private HashMap<String, StringBuilder> treeNodeHashMap = new HashMap<>();
    private ArrayList<TreeNode> leafNodes = new ArrayList<>();

    public TextCompressor(String words){
        splitWords(words);
        generateHashmapFrequentie();
        generateTreenodes();
        leafNodeToHashMap();
        writeBitFile();
    }

    private void splitWords(String words) {
        this.characterList = new ArrayList<>(Arrays.asList(words.split("")));
    }

    /**
     * This method makes a hashmap with the character and frequency of how often its used HashMap<String, Integer>
     */
    private void generateHashmapFrequentie() {
        map.clear();
        Set<String> set = new HashSet();
        set.addAll(characterList);
        for (String word : set) {
            map.put(word, 0);
        }

        for (String word : characterList) {
            int wordcount = map.get(word);
            map.put(word, wordcount + 1);
        }
    }

    /**
     * This method generates a TreeNode for every character and links them together to create one big Tree
     */
    private void generateTreenodes() {
        map.forEach((key, value) -> {
            TreeNode leafNode = new TreeNode(value, key);
            queue.offer(leafNode);
            leafNodes.add(leafNode);
        });

        while (queue.size() > 1) {
            queue.offer(new TreeNode(queue.poll(), queue.poll()));
        }
        rootNode = queue.peek();
    }

    /**
     * This method makes a HashMap of every character and its assigned code HashMap<String, StringBuilder>
     */
    private void leafNodeToHashMap() {
        for (TreeNode node : leafNodes) {
            if (node.getValue() != null) {
                treeNodeHashMap.put(node.getValue(), node.toBit());
            }
        }
    }

    private void writeBitFile(){
        StringBuilder bitString = new StringBuilder();
        for (String character : characterList) {
            bitString.append(treeNodeHashMap.get(character));
        }

        BitWriter bitWriter = new BitWriter(bitString,rootNode);
    }
}

class FrequentieComparator implements Comparator<Object> {
    public int compare(Object a, Object b) {
        TreeNode treeNodeA = (TreeNode) a;
        TreeNode treeNodeB = (TreeNode) b;

        if (treeNodeA.getFrequentie() >= treeNodeB.getFrequentie()) {
            return 1;
        } else {
            return -1;
        }
    }
}