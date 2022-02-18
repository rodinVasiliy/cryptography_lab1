import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {

    public static String getText(String path) {
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line = bf.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = bf.readLine();
            }
            return new String(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeText(String text, String path) {
        try {
            FileWriter writer = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("Select mode:");
            System.out.println("1) encrypt");
            System.out.println("2) decrypt");
            System.out.println("0) exit");
            Scanner console = new Scanner(System.in);
            int option = console.nextInt();
            if (option == 0) {
                return;
            }
            if (option == 1) {
                System.out.print("Enter the path of the text: ");
                console.nextLine();
                String path = console.nextLine();
                System.out.print("Enter the path of the encryptedText: ");
                String encryptedTextPath = console.nextLine();
                System.out.print("Enter the path of the key: ");
                String keyPath = console.nextLine();
                String text = getText(path);

                TextConverter textConverter = new TextConverter(Constants.ALPH);
                String convertedText = textConverter.convertText(text);
                int key = 0;
                File keyFile = new File(keyPath);
                try {
                    FileReader fileReader = new FileReader(keyFile);
                    Scanner fileScan = new Scanner(fileReader);
                    key = fileScan.nextInt();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return;
                }
                Encoder encoder = new Encoder(Constants.ALPH, key);

                String encodedText = encoder.encodeText(convertedText);
                writeText(encodedText, encryptedTextPath);
            }
            if (option == 2) {
                System.out.print("Enter the path of the encrypted text: ");
                console.nextLine();
                String encryptedTextPath = console.nextLine();
                System.out.print("Enter the path of the decrypted text: ");
                String decryptedTextPath = console.nextLine();

                String encryptedText = getText(encryptedTextPath);
                Decoder decoder = new Decoder(Constants.ALPH, encryptedText);
                String decodedText = decoder.decodeText();
                System.out.println(decodedText);
                writeText(decodedText, decryptedTextPath);

            }
        }

        /*String path = "C:\\Users\\HUAWEI\\IdeaProjects\\cryptography_lab1\\doyle_the_adventures.txt";
        String text = getText(path);
        if (text == null) {
            System.out.println("text not found");
            return;
        }
        TextConverter textConverter = new TextConverter(Constants.ALPH);
        String convertedText = textConverter.convertText(text);
        Encoder encoder = new Encoder(Constants.ALPH, 5);
        System.out.println(encoder.replacementAlphabetToString());

        String encodedText = encoder.encodeText(convertedText);
        System.out.println(encodedText);

        Decoder decoder = new Decoder(Constants.ALPH, encodedText);
        String decodedText = decoder.decodeText();
        System.out.println(decodedText);*/

    }

}
