import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BridgeRepair {

    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Long[]> problems = new ArrayList<>();
        Map<Integer, boolean[][]> permutation = new HashMap<>();
        int maxNums = 0;
        Long sum = 0L;

        lines = ReadReports.getRulesAndReports("./07day/input.txt", lines);

        maxNums = storeInMap(lines, problems);

        storePermutation(permutation, maxNums - 1);

        sum = iterateThroughProblems(problems, permutation);
        System.out.println(sum);

    }

    public static Long iterateThroughProblems(ArrayList<Long[]> problems, Map<Integer, boolean[][]> permutations) {
        Long total = 0L;

        for (Long[] problem : problems) {
            Long solution = problem[0];
            Long[] nums = new Long[problem.length - 1];
            for (int i = 1; i < problem.length; i++) {
                nums[i - 1] = problem[i];
            }
            int amountOfPerm = nums.length - 1;
            boolean[][] perms = permutations.get(amountOfPerm);

            if (findSum(solution, nums, perms))
                total += solution;
        }
        return total;
    }

    public static boolean findSum(Long solution, Long[] nums, boolean[][] perms) {

        for (int i = 0; i < perms.length; i++) {
            Long sum = perms[i][0] ? nums[0] + nums[1] : nums[0] * nums[1];

            for (int j = 1; j < perms[i].length; j++) {
                if (perms[i][j])
                    sum += nums[j + 1];
                else
                    sum *= nums[j + 1];
            }
            if (sum.equals(solution))
                return true;
        }

        return false;

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

    public static int storeInMap(ArrayList<String> lines, ArrayList<Long[]> problems) {
        int max = 0;
        for (String line : lines) {
            String[] parts = line.split(":");
            Long solution = Long.parseLong(parts[0]);

            String[] nums = parts[1].trim().split("\\s+");
            Long[] array = new Long[nums.length + 1];

            array[0] = solution;

            for (int i = 1; i < array.length; i++) {
                array[i] = Long.parseLong(nums[i - 1]);
            }

            problems.add(array);

            if (nums.length > max)
                max = nums.length;

        }
        return max;
    }

}