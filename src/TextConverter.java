import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class TextConverter {

    public TextConverter(char[] alphabet){
        for(char c : alphabet){
            alp.add(c);
        }
    }

    // алфавит разрешенных символов
    private final HashSet<Character> alp = new HashSet<>();

    public String convertText(String text) {
        String lowerCaseText = text.toLowerCase(Locale.ROOT);
        StringBuffer resultStr = new StringBuffer();

        for (int i = 0; i < lowerCaseText.length(); ++i) {
            char symbol = lowerCaseText.charAt(i);
            if (alp.contains(symbol)) {
                resultStr.append(symbol);
            }
            else {
                resultStr.append(symbol);
            }
        }
        return new String(resultStr);
    }

}
