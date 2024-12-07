import java.util.ArrayList;

public class RedNose {
    public static void main(String[] args) {
        ArrayList<int[]> nums = new ArrayList<>();

        nums = ReadReports.read("./dayTwo/input.txt");

        System.out.println("Have reports");

    }

    public static int readAndStoreReports() {

        return -1;
    }

    public static boolean isWithingRange(int[] nums, int[] trend, int left, int right) {

        int diff = nums[left] - nums[right];

        diff = calculateTrend(trend, diff, right);

        if (right > 1 && !checkTrend(trend, right))
            return false;

        if (1 < diff || diff > 3)
            return false;

        return true;
    }

    public static boolean checkTrend(int[] trend, int right) {

        if (trend[right - 1] != trend[right])
            return false;

        return true;
    }

    public static int calculateTrend(int[] trend, int diff, int right) {

        if (diff == 0)
            trend[right] = 0;
        else if (diff < 0) {
            diff = -diff;
            trend[right] = -1;
        } else {
            trend[right] = 1;
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
