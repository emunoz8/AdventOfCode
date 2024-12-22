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
                Long tot = concatResult(permutations, solution, nums);
                total += tot;
                if (tot == 0) {
                    for (int i = 0; i < problem.length; i++) {
                        System.out.print(problem[i] + " ");
                    }
                    System.out.println();
                }

            }

        }
        return total;
    }

    public static Long concatResult(Map<Integer, boolean[][]> permutations, Long solution, Long[] nums) {
        String stringSolution = String.valueOf(solution);

        for (int i = 1; i < stringSolution.length(); i++) {
            String left = stringSolution.substring(0, i);
            String right = stringSolution.substring(i, stringSolution.length());

            if (canConcat(permutations, nums, left, right)) {
                // System.out.println("The solution is " + solution + ": " + left + " " +
                // right);
                return solution;
            }

        }

        return 0L;
    }

    public static boolean canConcat(Map<Integer, boolean[][]> permutations, Long[] nums, String left, String right) {
        Long leftNum = Long.parseLong(left);
        Long rightNum = Long.parseLong(right);

        // maybe not
        if (leftNum.equals(nums[0]) && rightNum.equals(nums[1]))
            return true;

        boolean isFound = false;

        int splitIndex = 1;
        while (splitIndex < nums.length && !isFound) {
            boolean[][] perms = permutations.get(splitIndex);
            if (findSum(leftNum, nums, perms)) {
                isFound = true;
            }

            splitIndex++;
        }

        if (isFound && nums.length - splitIndex > 0) {
            Long[] rightNums = new Long[nums.length - splitIndex];
            for (int j = 0; j < rightNums.length; j++)
                rightNums[j] = nums[j + splitIndex];

            boolean[][] perms = permutations.get(rightNums.length - 1);

            if (findSum(rightNum, rightNums, perms)) {

                return true;
            }

        }
        return false;
    }

    public static boolean findSum(Long solution, Long[] nums, boolean[][] perms) {
        // System.out.print("\tthe solution is: " + solution + "\t\t");
        // for (int i = 0; i < nums.length; i++)
        // System.out.print(nums[i] + " ");
        // System.out.println();
        if (nums.length == 1) {
            if (solution.equals(nums[0]))
                return true;
            else
                return false;
        }

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