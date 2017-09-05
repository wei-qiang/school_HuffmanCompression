package logic;

import java.util.*;

/**
 * Created by wei-q on 29-Aug-17.
 */
public class Datastructurer {
    private ArrayList<String> words = new ArrayList<>();
    private HashSet hashSet = new HashSet();
    private TreeSet treeSet = new TreeSet(Collections.reverseOrder());
    private HashMap hashMap = new HashMap();

    public Datastructurer() {
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public HashSet getHashSet() {
        generateHashset();
        return hashSet;
    }

    public TreeSet getTreeSet() {
        generateTreeset();
        return treeSet;
    }

    public HashMap getHashMap() {
        generateHashmap();
        return hashMap;
    }

    public void setWords(String words) {
        this.words = new ArrayList<>(Arrays.asList(words.split(", |\n| ")));
        this.words.remove("");
    }

    public void generateHashset(){
        hashSet.clear();
        hashSet.addAll(words);
    }

    public void generateTreeset(){
        treeSet.clear();
        treeSet.addAll(words);
    }

    public void generateHashmap(){
        Set<String> set = getHashSet();
        for (String word: set){
            hashMap.put(word, 0);
        }

        for(String word:getWords()){
            int wordcount = (int) hashMap.get(word);
            hashMap.put(word, wordcount + 1);
        }

    }
}
