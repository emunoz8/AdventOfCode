public class RedNose {
    public static void main(String[] args) {
        int safeReports = 0;

        safeReports = readReports();

        System.out.println("The ammount of safe reports is: " + safeReports);

    }

    public static int readReports() {

        return -1;
    }

    public static boolean isWithingRange(int[] trend, int left, int right, int index) {

        int diff = left - right;

        diff = calculateTrend(trend, diff, index);

        if (index > 1 && !checkTrend(trend, index))
            return false;

        if (1 < diff || diff > 3)
            return false;

        return true;
    }

    public static boolean checkTrend(int[] trend, int index) {

        if (trend[index - 1] != trend[index])
            return false;

        return true;
    }

    public static int calculateTrend(int[] trend, int diff, int index) {

        if (diff == 0)
            trend[index] = 0;
        else if (diff < 0) {
            diff = -diff;
            trend[index] = -1;
        } else {
            trend[index] = 1;
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
