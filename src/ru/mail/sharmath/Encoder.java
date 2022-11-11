package ru.mail.sharmath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Encoder {
    public void encode(String filePath, int key) {
        String text = read(filePath);
        String encodedText = encodeText(text, key);
        write(filePath, encodedText, "(encoded)");
    }

    public void decode(String filePath, int key) {
        String text = read(filePath);
        String decodedText = decodeText(text, key);
        write(filePath, decodedText, "(decoded)");
    }

    private String decodeText(String text, int key) {
        key = trimKey(key);
        return encodeText(text, 26 - key);
    }

    private String encodeText(String text, int key) {
        char[] symbols = text.toCharArray();
        for (int i = 0; i < text.length(); i++) {
            symbols[i] = encodeSymbols(symbols[i], key);
        }
        return new String(symbols);
    }

    private char encodeSymbols(int symbol, int key) {
        key = trimKey(key);
        if (isSmallLetter(symbol)) {
            int encoded = symbol + key;
            return (char) returnCarriageIfNeededSmallLetter(encoded);
        }
        if (isCapitalLetter(symbol)) {
            int encoded = symbol + key;
            return (char) returnCarriageIfNeededCapitalLetter(encoded);
        }
        return (char) symbol;
    }

    private int returnCarriageIfNeededSmallLetter(int encoded) {
        return isSmallLetter(encoded) ? encoded : (encoded - 26);
    }

    private int returnCarriageIfNeededCapitalLetter(int encoded) {
        return isCapitalLetter(encoded) ? encoded : (encoded - 26);
    }

    private boolean isSmallLetter(int c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isCapitalLetter(int c) {
        return c >= 'A' && c <= 'Z';
    }

    private int trimKey(int key) {
        return key % 26;
    }

    public String read(String filePath) {
        Path path = Paths.get(filePath);
        String text = null;
        try {
            text = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public void write(String filePath, String text, String operationType) {
        Path path = Paths.get(filePath);
        try {
            StringBuilder builder = new StringBuilder(filePath);
            builder.insert(filePath.length() - 4, operationType);
            Path outFilePath = Paths.get(builder.toString());
            Files.writeString(outFilePath, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
