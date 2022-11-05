package ru.mail.sharmath;

public class Main {

    public static void main(String[] args) {
        String command = args[0];
        System.out.println(command);
        Encoder encoder = new Encoder();
        if ("encode".equals(command)) {
            String filePath = args[1];
            int key = Integer.parseInt(args[2]);
            encoder.encode(filePath, key);
        } else if ("decode".equals(command)) {
            String filePath = args[1];
            int key = Integer.parseInt(args[2]);
            encoder.decode(filePath,key);
        }
    }
}
