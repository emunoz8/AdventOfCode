import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BridgeRepair {

    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();
        Map<Long, int[]> problems = new LinkedHashMap<>();
        Map<Integer, boolean[][]> permutation = new HashMap<>();
        int maxNums = 0;

        lines = ReadReports.getRulesAndReports("./07day/input.txt", lines);

        maxNums = storeInMap(lines, problems);

        storePermutation(permutation, maxNums - 1);

    }

    public static void storePermutation(Map<Integer, boolean[][]> permutation, int n) {

        for (int i = 1; i <= n; i++) {
            boolean[][] perms = new boolean[(int) Math.pow(2, i)][i];
            for (int j = 0; j < perms.length; j++)
                for (int k = 0; k < i; k++)
                    perms[j][k] = (j & (1 << (i - k - 1))) != 0;

            permutation.put(i, perms);

        }

    }

    public static void printKeyValue(Map<Long, int[]> problems) {

        for (Map.Entry<Long, int[]> entry : problems.entrySet()) {
            Long key = entry.getKey();
            int[] value = entry.getValue();

            System.out.println("Key " + key + ", Value: ");
            for (int num : value) {
                System.out.print(num + " ");

            }
            System.out.println();
        }

    }

    public static int storeInMap(ArrayList<String> lines, Map<Long, int[]> problems) {
        int max = 0;
        for (String line : lines) {
            String[] parts = line.split(":");
            Long solution = Long.parseLong(parts[0]);

            String[] nums = parts[1].trim().split("\\s+");
            int[] array = new int[nums.length];

            for (int i = 0; i < nums.length; i++) {
                array[i] = Integer.parseInt(nums[i]);
            }

            problems.put(solution, array);

            if (nums.length > max)
                max = nums.length;

        }
        return max;
    }

}