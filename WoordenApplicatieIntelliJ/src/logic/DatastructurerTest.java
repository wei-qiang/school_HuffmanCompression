package logic;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class DatastructurerTest {
    Datastructurer dataStructurer = new Datastructurer();

    @Test
    void getSetWords() {
        dataStructurer.setWords("hoi, ik");
        Assert.assertEquals("hoi", dataStructurer.getWords().get(0));
    }

    @Test
    void getSetSentence() {
        dataStructurer.setSentence("hoi, ik \n  hallo");
        Assert.assertEquals("hoi, ik ", dataStructurer.getSentence().get(0));
    }

    @Test
    void generateHashsetAantal() {
        dataStructurer.setWords("hoi, hoi, ik");
        dataStructurer.generateHashsetAantal();
        Assert.assertEquals("[ik, hoi]", dataStructurer.getHashSet().toString());
    }

    @Test
    void generateTreesetSorteer() {
        dataStructurer.setWords("hoi, allemaal, jij");
        dataStructurer.generateTreesetSorteer();
        Assert.assertEquals("[jij, hoi, allemaal]", dataStructurer.getTreeSet().toString());
    }

    @Test
    void generateHashmapFrequentie() {
        dataStructurer.setWords("hoi, hoi, hoi, allemaal, hallo, allemaal");
        dataStructurer.generateHashmapFrequentie();
        Assert.assertEquals("{hallo=1, hoi=3, allemaal=2}", dataStructurer.getHashMap(true).toString());
    }

    @Test
    void generateHashmapConcordantie() {
        dataStructurer.setWords("hoi ik \n hoi jij \n jij bent");
        dataStructurer.setSentence("hoi ik \n hoi jij \n jij bent");
        Assert.assertEquals("{ik= 1, , hoi= 1,  2, , bent= 3, , jij= 2,  3, }", dataStructurer.getHashMap(false).toString());
    }

}