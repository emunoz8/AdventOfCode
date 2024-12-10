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
            } else if (isGoodReport(num, true)) {
                count++;
            } else {
                printReport(num);
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
        return removeNumberAtIndex(nums, left) || removeNumberAtIndex(nums, right);
    }

    public static boolean removeNumberAtIndex(int[] nums, int ignore) {
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

// public static int[] removeError(int[] nums, int errorIndex) {
// int[] returnArr = new int[nums.length - 1];

// for (int i = 0, j = 0; i < nums.length; i++, j++) {
// if (i == errorIndex)
// j--;
// else {
// returnArr[j] = nums[i];
// }
// }

// return returnArr;
// }

// public static int findErrorIndex(int[] trend, int index) {
// int errorIndex = -1;
// if (index + 1 == trend.length && !isTrending(trend, index))
// errorIndex = index;
// else if (index + 1 < trend.length && !isTrending(trend, index) &&
// !isTrending(trend, index + 1))
// errorIndex = index;

// return errorIndex;
// }

// public static boolean removeError(int[] nums, int errorIndex) {
// int[] newNums = new int[nums.length - 1];
// int[] newTrend = new int[newNums.length - 1];

// for (int i = 0, j = 0; i < nums.length; i++, j++) {
// if (i == errorIndex)
// j--;
// else {
// newNums[j] = nums[i];
// }
// }

// for (int i = 0; i < newNums.length; i++) {
// if (!isWithinRange(newNums, newTrend, i - 1, i))
// return false;
// }
// return true;
// }

// public static boolean hasOneError(int[] nums, int[] trend) {
// int errorIndex = -1;
// int carry = 0;

// for (int i = 1; i < trend.length && errorIndex != i - 1; i++) {
// if (findErrorIndex(trend, i) != -1) {
// errorIndex = i;

// }
// }

// if (errorIndex != -1) {
// return removeError(nums, errorIndex);
// }

// for (int i = 0; i < trend.length; i++) {
// if (errorIndex != i && !inRange(trend[i] + carry))
// return false;

// carry = errorIndex == i ? trend[errorIndex] : 0;
// }

// return true;
// }

// public static boolean trendOfTrends(int[] nums, int[] trend, int index,
// boolean isRightNeighbor) {
// if (index - 1 < 0 || index + 1 > trend.length)
// return false;
// if (index + 2 == nums.length)
// return true;

// if (isRightNeighbor)
// trend[index] = nums[index] - nums[index + 2];
// else
// trend[index] = nums[index - 1] - nums[index + 1];

// return isTrending(trend, index);

// }