import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

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

}