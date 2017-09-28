package sample;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class TextCompressor {
    private ArrayList<String> characterList = new ArrayList<>();
    private HashMap<String, Integer> map = new HashMap<>();
    private PriorityQueue queue = new PriorityQueue(1000, new FrequentieComparator());

    public TextCompressor(String words) {
        splitWords(words);
        generateHashmapFrequentie();
        generateTreenodes();

    }

    public void splitWords(String words) {
        this.characterList = new ArrayList<>(Arrays.asList(words.split("")));
    }

    public void generateHashmapFrequentie() {
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

    public void generateTreenodes(){
        map.forEach((key, value) -> {
            queue.offer(new TreeNode(value, key));
        });

        while (queue.size() > 1){
            queue.offer(new TreeNode((TreeNode) queue.poll(), (TreeNode) queue.poll()));
        }
    }
}

class FrequentieComparator implements Comparator<Object> {
    public int compare(Object a, Object b) {
        TreeNode treeNodeA = (TreeNode)a;
        TreeNode treeNodeB = (TreeNode)b;

        if (treeNodeA.getFrequentie() >= treeNodeB.getFrequentie()) {
            return 1;
        } else {
            return -1;
        }
    }
}