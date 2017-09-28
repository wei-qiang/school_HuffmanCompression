package sample;

import java.util.*;

public class TextCompressor {
    private ArrayList<String> characterList = new ArrayList<>();
    private HashMap<String, Integer> map = new HashMap<>();         //hashmap voor de frequentie functie

    public TextCompressor(String words) {
        splitWords(words);
        generateHashmapFrequentie();
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
}