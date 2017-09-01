package logic;

import java.util.*;

/**
 * Created by wei-q on 29-Aug-17.
 */
public class Datastructurer {
    private ArrayList<String> words = new ArrayList<>();
    private HashSet hashSet = new HashSet();
    private TreeSet treeSet = new TreeSet(Collections.reverseOrder());

    public Datastructurer() {
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public HashSet getHashSet() {
        GenerateHashset();
        return hashSet;
    }

    public TreeSet getTreeSet() {
        GenerateTreeset();
        return treeSet;
    }

    public void setWords(String words) {
        this.words = new ArrayList<>(Arrays.asList(words.split(", |\n| ")));
        this.words.remove("");
    }

    public void GenerateHashset(){
        hashSet.clear();
        hashSet.addAll(words);
    }

    public void GenerateTreeset(){
        treeSet.clear();
        treeSet.addAll(words);
    }
}
