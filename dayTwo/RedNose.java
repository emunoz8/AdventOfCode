import java.util.ArrayList;

public class RedNose {
    public static void main(String[] args) {
        ArrayList<int[]> nums = new ArrayList<>();
        int goodReports = 0;

        nums = ReadReports.read("./dayTwo/input.txt");

        goodReports = iterateThroughReports(nums);

        System.out.println("The amount of Good Reports is: " + goodReports);

    }

    public static int iterateThroughReports(ArrayList<int[]> nums) {
        int count = 0;
        for (int[] num : nums) {
            if (isGoodReport(num))
                count++;
        }

        return count;
    }

    public static boolean isGoodReport(int[] nums) {
        int[] trend = new int[nums.length - 1];

        for (int i = 1; i < nums.length; i++) {
            if (!isWithingRange(nums, trend, i - 1, i))
                return false;
        }

        return true;
    }

    public static boolean isWithingRange(int[] nums, int[] trend, int left, int right) {

        int diff = nums[left] - nums[right];

        diff = calculateTrend(trend, diff, left);

        if (right > 1 && !checkTrend(trend, left - 1, left))
            return false;

        if (1 > diff || diff > 3)
            return false;

        return true;
    }

    public static boolean checkTrend(int[] trend, int left, int right) {

        if (trend[left] != trend[right])
            return false;

        return true;
    }

    public static int calculateTrend(int[] trend, int diff, int left) {

        if (diff == 0)
            trend[left] = 0;
        else if (diff < 0) {
            diff = -diff;
            trend[left] = -1;
        } else {
            trend[left] = 1;
        }

        return diff;
    }

    public static boolean isTrendConsistent(int[] trend) {

        for (int i = 1; i < trend.length; i++)
            if (trend[i - 1] != trend[i])
                return false;

        return true;
    }

}
