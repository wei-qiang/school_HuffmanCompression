package sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BitReader {
    
    public StringBuilder readBits(String fileName) {
        byte[] savedByte = null;
        Path path = Paths.get(fileName);
        StringBuilder bitString = new StringBuilder();
        try {
            savedByte = Files.readAllBytes(path);
        } catch (IOException e) {
            Logger.getLogger(BitReader.class.getName()).log(Level.SEVERE, null, e);
        }

        if (savedByte != null) {
            for (byte b : savedByte) {
                StringBuilder bytestring = new StringBuilder();
                bytestring.append(Integer.toBinaryString(b & 0xFF));
                while (bytestring.length() < 7) {
                    bytestring.insert(0, "0");
                }
                bitString.append(bytestring);
            }
        }
        return bitString;
    }
}
