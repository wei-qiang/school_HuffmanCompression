package sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BitReader {
    private byte[] savedByte;
    private StringBuilder bitString;

    public BitReader() {
    }

    public StringBuilder readBits(String fileName) {
        Path path = Paths.get(fileName);
        bitString = new StringBuilder();
        try {
            savedByte = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
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
