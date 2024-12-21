import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.FileReader;

public class ReadReports {

    public static ArrayList<int[]> read(String filePath) {
        ArrayList<int[]> wholeReport = new ArrayList<>();
        try {
            Files.lines(Paths.get(filePath)).forEach(line -> {
                wholeReport.add(getNumberArrays(line));
            });
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return wholeReport;
    }

    public static int[] getNumberArrays(String line) {
        String[] parts = line.split("\\s+");
        int[] nums = new int[parts.length];

        for (int i = 0; i < parts.length; i++)
            nums[i] = Integer.parseInt(parts[i]);

        return nums;
    }

    public static String getString(String filePath) {
        Path path = Path.of(filePath);
        String rString = "Didnt work";

        try {
            rString = Files.readString(path);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return rString;
    }

    public static char[][] set2DArray(String filePath) {
        char[][] rArr = { {} };

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<char[]> list = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                char[] charArray = line.toCharArray();
                list.add(charArray);

            }
            int rows = list.size();
            int cols = list.get(0).length;
            rArr = new char[rows][cols];

            for (int i = 0; i < rows; i++)
                rArr[i] = list.get(i);

        } catch (Exception e) {
            System.out.println("Error: " + e);

        }

        return rArr;

    }

    public static ArrayList<String> getRulesAndReports(String filePath, ArrayList<String> reports) {
        ArrayList<String> list = new ArrayList<>();
        boolean isReports = false;

        try {
            for (String line : Files.readAllLines(Paths.get(filePath))) {
                if (line.equals("")) {
                    isReports = true;
                    continue;
                }
                if (!isReports)
                    list.add(line);
                if (isReports)
                    reports.add(line);

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }

}