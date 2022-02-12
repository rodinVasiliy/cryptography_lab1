import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Decoder {

    /** количество вхождений */
    private HashMap<Character, Integer> numberOfOccurrences = new HashMap<>();

    private Integer key;

    private final char[] alphabet;

    private HashMap<Character, Character> replacementAlphabet = new HashMap<>();

    int maxNumberOfOccurrences;

    String encodedText;

    public Decoder(char[] alp, String textToDecode) {
        alphabet = alp.clone();
        for (char s : alphabet) {
            numberOfOccurrences.put(s, 0);
        }
        encodedText = textToDecode;
    }

    public String replacementAlphabetToString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<Character, Character> entry : replacementAlphabet.entrySet()) {
            stringBuffer.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return new String(stringBuffer);
    }

    public String numberOfOccurrencesToString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<Character, Integer> entry : numberOfOccurrences.entrySet()) {
            stringBuffer.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return new String(stringBuffer);
    }

    public void calculateNumberOfOccurrences() {
        for (int i = 0; i < encodedText.length(); ++i) {
            char s = encodedText.charAt(i);
            int numberOfOccurrencesOfSymbol = numberOfOccurrences.get(s) + 1;
            numberOfOccurrences.replace(s, numberOfOccurrencesOfSymbol);
        }
    }

    public int calculateKey() {
        // костыль, потом уберу наверное)
        numberOfOccurrences.remove(' ');
        // https://askdev.ru/q/poisk-klyucha-svyazannogo-s-maksimalnym-znacheniem-na-karte-java-23846/
        Character character =
                Collections.max(numberOfOccurrences.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        String alp = new String(alphabet);
        Character maxOften = Constants.oftenAsPossibleCharacter;
        int position1 = alp.indexOf(maxOften);
        int position2 = alp.indexOf(character);
        key = (position2 - position1) % Constants.COUNT_OF_REPLACEABLE_SYMBOLS;
        return key;
    }

    public void calculateReplacementAlphabet() {
        for (int i = 0; i < Constants.COUNT_OF_REPLACEABLE_SYMBOLS; ++i) {
            char s = alphabet[i];
            int positionReplaseS = (i - key);
            if (positionReplaseS < 0) {
                positionReplaseS += Constants.COUNT_OF_REPLACEABLE_SYMBOLS;
            }
            char replaseS = alphabet[positionReplaseS];
            replacementAlphabet.put(s, replaseS);
        }
        for (int i = Constants.COUNT_OF_REPLACEABLE_SYMBOLS; i < alphabet.length; ++i) {
            char s = alphabet[i];
            replacementAlphabet.put(s, s);
        }
    }

    public String decodeText() {
        calculateNumberOfOccurrences();
        calculateKey();
        calculateReplacementAlphabet();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < encodedText.length(); ++i) {
            char s = encodedText.charAt(i);
            char replaseS = replacementAlphabet.get(s);
            sb.append(replaseS);
        }
        return new String(sb);
    }

}
