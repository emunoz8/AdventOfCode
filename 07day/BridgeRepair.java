import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BridgeRepair {

    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Long[]> problems = new ArrayList<>();
        Map<Integer, boolean[][]> permutation = new HashMap<>();
        int maxNums = 0;
        Long sum = 0L;
        lines = ReadReports.getRulesAndReports("./07day/input.txt", lines);
        maxNums = storeInArrayList(lines, problems);
        storePermutation(permutation, maxNums);
        sum = iterateThroughProblems(problems, permutation);
        System.out.println(sum);

    }

    public static Long iterateThroughProblems(ArrayList<Long[]> problems, Map<Integer, boolean[][]> permutations) {
        Long total = 0L;
        Long rechecks = 0L;

        for (Long[] problem : problems) {
            Long solution = problem[0];
            Long[] nums = new Long[problem.length - 1];
            for (int i = 1; i < problem.length; i++)
                nums[i - 1] = problem[i];
            int amountOfPerm = nums.length - 1;
            boolean[][] perms = permutations.get(amountOfPerm);

            if (findSum(solution, nums, perms))
                total += solution;
            else {
                rechecks += canConcat(solution, nums);
            }

        }

        System.out.println("the rechecks: " + rechecks + "\nThe total: " + total);
        return total + rechecks;
    }

    public static Long canConcat(Long solution, Long[] nums) {
        int n = nums.length - 1;
        int totalPermutations = (int) Math.pow(3, n);
        int[][] permutations = new int[totalPermutations][n];
        int[] current = new int[n];
        int[] rowIndex = { 0 };
        permutationOfThree(permutations, current, n, 0, rowIndex);

        if (iteratePermutations(permutations, nums, solution))
            return solution;

        return 0L;
    }

    public static boolean iteratePermutations(int[][] permutations, Long[] nums, Long solution) {

        for (int i = 0; i < permutations.length; i++) {
            ArrayList<String> concat = new ArrayList<>();
            Long sum = nums[0];

            for (int j = 1; j < nums.length; j++) {
                if (permutations[i][j - 1] == -1) {
                    sum += nums[j];
                } else if (permutations[i][j - 1] == 1) {
                    sum *= nums[j];
                } else {
                    String sumString = String.valueOf(sum) + String.valueOf(nums[j]);
                    sum = Long.parseLong(sumString);
                }
            }

            concat.add(String.valueOf(sum));
            String candidate = "";
            for (String num : concat)
                candidate += num;

            if (candidate.equals(String.valueOf(solution))) {
                return true;
            }

        }
        System.out.println(solution);

        return false;
    }

    // 3^n, -1, 0, 1 concat, mult, add
    public static void permutationOfThree(int[][] permutations, int[] current, int n, int pos, int[] rowIndex) {
        if (pos == n) {
            System.arraycopy(current, 0, permutations[rowIndex[0]], 0, n);
            rowIndex[0]++; // Move to the next row
            return;
        }
        for (int i = -1; i <= 1; i++) {
            current[pos] = i;
            permutationOfThree(permutations, current, n, pos + 1, rowIndex);
        }
    }

    public static boolean findSum(Long solution, Long[] nums, boolean[][] perms) {

        for (int i = 0; i < perms.length; i++) {
            Long sum = perms[i][0] ? nums[0] + nums[1] : nums[0] * nums[1];
            for (int j = 1; j < perms[i].length; j++) {
                if (sum.compareTo(solution) > 0)
                    break;
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

        for (int i = 0; i <= n; i++) {
            boolean[][] perms = new boolean[(int) Math.pow(2, i)][i];
            for (int j = 0; j < perms.length; j++)
                for (int k = 0; k < i; k++)
                    perms[j][k] = (j & (1 << (i - k - 1))) != 0;
            permutation.put(i, perms);
        }
    }

    public static int storeInArrayList(ArrayList<String> lines, ArrayList<Long[]> problems) {
        int max = 0;
        for (String line : lines) {
            String[] parts = line.split(":");
            Long solution = Long.parseLong(parts[0]);
            String[] nums = parts[1].trim().split("\\s+");
            Long[] array = new Long[nums.length + 1];

            array[0] = solution;

            for (int i = 1; i < array.length; i++)
                array[i] = Long.parseLong(nums[i - 1]);

            problems.add(array);

            if (nums.length > max)
                max = nums.length;
        }
        return max;
    }
}