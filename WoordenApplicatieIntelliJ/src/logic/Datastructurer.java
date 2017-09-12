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
    private HashMap hashMap = new HashMap();   //hashmap voor de concordantie functie

    private HashMap<String, Double> map = new HashMap<String, Double>();         //hashmap voor de frequentie functie
    private ValueComparator bvc = new ValueComparator(map);
    private TreeMap<String, Double> sorted_map = new TreeMap<String, Double>(bvc);


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

    public HashMap getHashMap() {
        generateHashmapConcordantie();
        return hashMap;
    }

    public TreeMap getTreeMap(){
        generateHashmapFrequentie();
        return sorted_map;
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
        map.clear();
        Set<String> set = getHashSet();
        for (String word: set){
            map.put(word, 0.0);
        }

        for(String word:getWords()){
            double wordcount = (double) map.get(word);
            map.put(word, wordcount + 1.0);
        }

        sorted_map.putAll(map);

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

class ValueComparator implements Comparator<String> {
    Map<String, Double> base;

    public ValueComparator(Map<String, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with
    // equals.
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}



