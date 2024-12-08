import java.util.ArrayList;
import java.util.LinkedList;

import org.w3c.dom.Node;

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
            if (isGoodReport(num)) {// part 1
                count++;
                System.out.print("Pass : ");

            } else if (dampenerReport(num)) {// part 2
                count++;

            }

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

    public static boolean dampenerReport(int[] nums) {

        return true;
    }

    public static boolean isWithingRange(int[] nums, int[] trend, int left, int right) {

        int diff = nums[left] - nums[right];

        trend[left] = diff;

        if (right > 1 && !isTrending(trend, left - 1, left))
            return false;

        return inRange(diff);
    }

    public static boolean isTrending(int[] trend, int left, int right) {

        if ((trend[left] > 0 && trend[right] > 0) || (trend[left] < 0 && trend[right] < 0))
            return true;

        return false;
    }

    public static boolean inRange(int value) {
        return ((1 <= value && value <= 3) || (-3 <= value && value <= -1));
    }

    public static void printReport(int[] nums) {
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + " ");
        System.out.println();

    }

}

// public static boolean isTrendConsistent(int[] trend) {

// for (int i = 1; i < trend.length; i++)
// if (trend[i - 1] != trend[i])
// return false;

// return true;
// }

// public static boolean dampenerGoodReport(int[] nums) {
// int[] absoluteDifference = new int[nums.length - 1];
// int[] trend = new int[nums.length - 1];
// int discrepencies = 0;

// setAbsoluteDifference(nums, absoluteDifference, trend);

// if (!inRange(absoluteDifference[0]))
// discrepencies++;

// for (int i = 1; i < trend.length; i++) {

// if (!checkTrend(trend, i - 1, i) || !inRange(absoluteDifference[i])) {
// discrepencies++;
// }
// if (discrepencies > 1)
// return false;
// }

// return true;
// }

// public static void setAbsoluteDifference(int[] nums, int[] absDiff, int[]
// trend) {
// int diff = 0;
// for (int i = 1; i < nums.length; i++) {
// diff = nums[i - 1] - nums[i];
// diff = calculateTrend(trend, diff, i - 1);
// absDiff[i - 1] = diff;
// }
// }

// public static int calculateTrend(int[] trend, int diff, int left) {

// if (diff == 0)
// trend[left] = 0;
// else if (diff < 0) {
// diff = -diff;
// trend[left] = -1;
// } else {
// trend[left] = 1;
// }

// return diff;

// }
