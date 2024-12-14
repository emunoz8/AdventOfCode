import java.util.regex.*;
import java.util.ArrayList;

public class MullItOver {
    public static void main(String[] args) {
        String input = ReadReports.getString("./dayThree/input.txt");
        String regex = "mul\\(\\d+,\\d+\\)|don\\'t\\(\\)|do\\(\\)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        int total = 0;
        boolean isActive = true;

        while (matcher.find()) {
            String match = matcher.group();
            System.out.println(match);
            if (match.equals("do()")) {
                isActive = true;
            } else if (match.equals("don't()")) {
                isActive = false;
            } else if (isActive) {
                match = matcher.group().substring(4, matcher.group().length() - 1);
                int index = 0;
                index = findCommaIndex(match);
                total += multiplyValues(match, index);
            }
        }

        System.out.println(total);
    }

    public static int findCommaIndex(String match) {
        int i = 0;
        while (Character.isDigit(match.charAt(i)))
            i++;
        return i;
    }

    public static int multiplyValues(String matches, int index) {

        return Integer.parseInt(matches.substring(0, index))
                * Integer.parseInt(matches.substring(index + 1, matches.length()));

    }

}