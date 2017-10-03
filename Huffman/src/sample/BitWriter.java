package sample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BitWriter {
    private char[] bitChars;
    private byte[] byteArray;

    /**
     * deze methode schrijft de bits naar een bestand
     * @param bitString
     * @param rootnode
     * @param path
     */
    public void writeBits(StringBuilder bitString, TreeNode rootnode, String path) {
        toCharArray(bitString);
        toByteArray();
        writeFile(rootnode, path);
    }

    private void toCharArray(StringBuilder bitString) {
        char[] preBitChars = bitString.toString().toCharArray();
        int bitShortage = (8 - (preBitChars.length % 8));
        bitChars = new char[preBitChars.length + bitShortage];
        System.arraycopy(preBitChars, 0, bitChars, 0, preBitChars.length);

        for (int i = 0; i < bitShortage; i++) {
            bitChars[preBitChars.length + i] = '0';
        }
    }

    private void toByteArray() {
        byteArray = new byte[bitChars.length / 7 + 1];

        StringBuilder byt = new StringBuilder();
        int counter = 0;
        for (char bit : bitChars) {
            byt.append(bit);
            if (byt.length() == 7) {
                byteArray[counter] = (byte) Integer.parseInt(byt.toString(), 2);
                byt = new StringBuilder();
                counter++;
            }
        }
        while (byt.length() < 7) {
            byt.append("0");
        }
        byteArray[counter] = Byte.parseByte(byt.toString(), 2);
    }

    private void writeFile(TreeNode rootNode, String path) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        Path file = Paths.get(path);
        try {
            Files.write(file, byteArray);
        } catch (IOException e) {
            Logger.getLogger(BitWriter.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            fileOutputStream = new FileOutputStream(path + "Tree.ser");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(rootNode);
        } catch (IOException e) {
            Logger.getLogger(BitWriter.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    Logger.getLogger(BitWriter.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    Logger.getLogger(BitWriter.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
