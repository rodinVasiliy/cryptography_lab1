import java.util.HashMap;
import java.util.Map;

public class Encoder {

    private final HashMap<Character, Character> replacementAlphabet = new HashMap<>();

    public String replacementAlphabetToString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<Character, Character> entry : replacementAlphabet.entrySet()) {
            stringBuffer.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return new String(stringBuffer);
    }

    public Encoder(char[] alphabet, Integer key) {
        StringBuffer newAlp = new StringBuffer();

        /* alphabet without '_', '-', '\n' */
        for (int i = 0; i < Constants.COUNT_OF_REPLACEABLE_SYMBOLS; ++i) {
            newAlp.append(alphabet[i]);
        }
        newAlp.append(newAlp);

        for (int i = 0; i < Constants.COUNT_OF_REPLACEABLE_SYMBOLS; ++i) {
            char s = newAlp.charAt(i);
            char replaseS = newAlp.charAt(i + key);
            replacementAlphabet.put(s, replaseS);
        }

        for (int i = Constants.COUNT_OF_REPLACEABLE_SYMBOLS; i < alphabet.length; ++i) {
            char s = alphabet[i];
            replacementAlphabet.put(s, s);
        }

    }

    public String encodeText(String text) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < text.length(); ++i) {
            char s = text.charAt(i);
            char replaseS = replacementAlphabet.get(s);
            sb.append(replaseS);
        }
        return new String(sb);
    }
}
