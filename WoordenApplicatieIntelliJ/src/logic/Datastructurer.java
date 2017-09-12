package logic;

import java.util.*;

/**
 * Created by wei-q on 29-Aug-17.
 */
public class Datastructurer {
    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<String> sentence = new ArrayList<>();
    private HashSet hashSet = new HashSet();
    private TreeSet treeSet = new TreeSet(Collections.reverseOrder());
    private HashMap hashMap = new HashMap();

    public Datastructurer() {
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public ArrayList<String> getSentence(){
        return sentence;
    }

    public HashSet getHashSet() {
        generateHashsetAantal();
        return hashSet;
    }

    public TreeSet getTreeSet() {
        generateTreesetSorteer();
        return treeSet;
    }

    //boolean frequentie true genereert de frequentie opdracht
    public HashMap getHashMap(boolean frequentie) {
        if(frequentie){
            generateHashmapFrequentie();
        }
        else{
            generateHashmapConcordantie();
        }
        return hashMap;
    }

    public void setWords(String words) {
        this.words = new ArrayList<>(Arrays.asList(words.split(", |\n| ")));
        while(this.words.contains("")) {
            this.words.remove("");
        }
    }

    public void setSentence(String words){
        this.sentence = new ArrayList<>(Arrays.asList(words.split("\n")));
    }

    public void generateHashsetAantal(){
        hashSet.clear();
        hashSet.addAll(words);
    }

    public void generateTreesetSorteer(){
        treeSet.clear();
        treeSet.addAll(words);
    }

    public void generateHashmapFrequentie(){
        hashMap.clear();
        Set<String> set = getHashSet();
        for (String word: set){
            hashMap.put(word, 0);
        }

        for(String word:getWords()){
            int wordcount = (int) hashMap.get(word);
            hashMap.put(word, wordcount + 1);
        }
    }

    public void generateHashmapConcordantie(){
        hashMap.clear();
        int counter = 1;

        Set<String> set = getHashSet();
        for (String word: set){
           hashMap.put(word,"");
        }

        for(String sentence:sentence){
            setWords(sentence);
            
            for(String word: words){
                String sentenceln = (String) hashMap.get(word);
                hashMap.put(word, sentenceln+ " " + counter + ", ");
            }
            counter = counter + 1;
        }
    }
}
