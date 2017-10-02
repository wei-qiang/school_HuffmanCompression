package sample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BitWriter {
    private char[] preBitChars;
    private int bitShortage;
    private char[] bitChars;
    private byte[] byteArray;


    public BitWriter(StringBuilder bitString, TreeNode rootnode) {
        toCharArray(bitString);
        toByteArray();
        writeFile(rootnode);
    }

    private void toCharArray(StringBuilder bitString) {
        preBitChars = bitString.toString().toCharArray();
        bitShortage = (8 - (preBitChars.length % 8));
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
        byteArray[counter] = Byte.parseByte(byt.toString(), 2);

    }

    private void writeFile(TreeNode rootNode) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        Path file = Paths.get("test");
        try {
            Files.write(file, byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fileOutputStream = new FileOutputStream("testTree.ser");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
