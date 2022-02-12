import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
                line = bf.readLine();
            }
            return new String(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String path = "C:\\IDEA_Projects\\cryptography_lab1\\doyle_the_adventures.txt";
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
        System.out.println(decodedText);

    }

}
