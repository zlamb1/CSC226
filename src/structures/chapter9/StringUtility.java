package structures.chapter9;

public class StringUtility {
    public static String repeat(char c, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
