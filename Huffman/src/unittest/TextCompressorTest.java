package unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sample.TextCompressor;
import sample.TextDecrompressor;
import sample.TreeNode;

import static org.junit.jupiter.api.Assertions.*;

class TextCompressorTest {
    TextCompressor compressor;
    TreeNode rootNode;

    @BeforeEach
    void setUp() {
        compressor = new TextCompressor();
        rootNode = compressor.generateTreenodes("test treenode");
    }

    @Test
    void generateTreenodes() {
        assertEquals(13, rootNode.getFrequentie());
    }

    @Test
    void makeBitString() {
        compressor.makeBitString();
        TextDecrompressor decrompressor = new TextDecrompressor();
        decrompressor.decompressBits(compressor.makeBitString(), rootNode);
    }
}