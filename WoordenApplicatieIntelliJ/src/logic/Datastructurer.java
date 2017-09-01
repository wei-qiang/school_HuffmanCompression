package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by wei-q on 29-Aug-17.
 */
public class Datastructurer {
    private ArrayList<String> words = new ArrayList<>();
    private HashSet hashSet = new HashSet();

    public Datastructurer() {
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public HashSet getHashSet() {
        GenerateHashset();
        return hashSet;
    }

    public void setWords(String words) {
        this.words = new ArrayList<>(Arrays.asList(words.split("\\s*,\\s*")));
    }

    public void GenerateHashset(){
        hashSet.clear();
        hashSet.addAll(words);
    }
}
