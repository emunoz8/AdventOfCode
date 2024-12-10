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
            if (isGoodReport(num, false)) {// part 1
                count++;
            } else if (isGoodReport(num, true)) {// part 2
                count++;
            }
        }
        return count;
    }

    public static boolean isGoodReport(int[] nums, boolean hasDampener) {
        int[] trend = new int[nums.length - 1];
        return iterateThroughLine(nums, trend, hasDampener);
    }

    public static boolean iterateThroughLine(int[] nums, int[] trend, boolean hasDampener) {
        if (hasDampener) {

            for (int i = 1; i < nums.length; i++) {
                if (!isWithinRange(nums, trend, i - 1, i) && !checkNeighbor(nums, i - 1, i)) {
                    return false;
                }
            }

        } else {
            for (int i = 1; i < nums.length; i++) {
                if (!isWithinRange(nums, trend, i - 1, i))
                    return false;
            }
        }

        return true;
    }

    public static boolean checkNeighbor(int[] nums, int left, int right) {
        return removeNumberAtIndex(nums, left) || removeNumberAtIndex(nums, right) ||
                removeNumberAtIndex(nums, left - 1) || removeNumberAtIndex(nums, right + 1);
    }

    public static boolean removeNumberAtIndex(int[] nums, int ignore) {
        if (ignore < 0 || ignore > nums.length - 1)
            return false;
        int[] rArr = new int[nums.length - 1];
        int[] trend = new int[rArr.length - 1];
        for (int i = 0, j = 0; i < nums.length; i++, j++) {
            if (ignore != i)
                rArr[j] = nums[i];
            else
                j--;
        }
        for (int i = 1; i < rArr.length; i++)
            if (!isWithinRange(rArr, trend, i - 1, i))
                return false;
        return true;
    }

    public static boolean isWithinRange(int[] nums, int[] trend, int left, int right) {
        int diff = nums[left] - nums[right];
        trend[left] = diff;
        if (right > 1 && !isTrending(trend, left))
            return false;
        return inRange(diff);
    }

    public static boolean isTrending(int[] trend, int right) {
        int left = right - 1;
        if (trend[left] == 0 && trend[right] == 0)
            return false;
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