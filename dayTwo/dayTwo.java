import java.nio.file.Files;
import java.nio.file.Paths;

public class dayTwo {

    public static void main(String[] args) {
        String filePath = "./input.txt";
        int totalSafe = 0;

        totalSafe = iterateThroughReports(filePath);

        System.out.println("Total Safe: " + totalSafe);

    }

    public static int iterateThroughReports(String filePath) {

        boolean[] isSafe = { false };
        int[] totalSafe = { 0 };

        try {
            Files.lines(Paths.get(filePath)).forEach(line -> {
                String[] parts = line.split(" ");

                int i = 1;
                int previous = Integer.parseInt(parts[i - 1]);
                int current = Integer.parseInt(parts[i]);

                // Part 1
                if (previous < current) {
                    isSafe[0] = isItSafe(parts, previous, current, ++i, true);
                } else {
                    isSafe[0] = isItSafe(parts, previous, current, ++i, false);
                }

                // Part 2
                if (!isSafe[0]) {

                    isSafe[0] = problemDampener(parts);
                    // testing
                    for (String part : parts) {
                        System.out.print(part + " ");
                    }
                    System.out.println(": " + isSafe[0]);

                    // //////////////////////
                }

                if (isSafe[0]) {
                    totalSafe[0]++;
                    isSafe[0] = false;
                }

            });

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return totalSafe[0];

    }

    public static boolean isItSafe(String[] parts, int previous, int current, int index, boolean isIncreasing) {

        int sum = isIncreasing ? current - previous : previous - current;

        if (index == parts.length && sum >= 1 && sum <= 3) {
            return true;
        }
        if ((sum < 1 || sum > 3)) {
            return false;
        } else {
            return isItSafe(parts, Integer.parseInt(parts[index - 1]),
                    Integer.parseInt(parts[index]), index + 1,
                    isIncreasing);
        }
    }

    public static boolean problemDampener(String[] parts) {

        int lastIndex = parts.length - 1;
        int[] nums = new int[lastIndex + 1];
        boolean[] isDeacreasingComparison = new boolean[lastIndex];
        int current = 1, ignore = -1;

        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }

        while (current <= lastIndex) {

            if (!withinRange(isDeacreasingComparison, nums, current - 1, current)) {

                if (ignore != -1)
                    return false;

                if (nums[current - 1] - nums[current] == 0) {
                    ignore = current - 1;
                }
                if (checkNeighbor(isDeacreasingComparison, nums, current - 1, current + 1)) {
                    ignore = current;
                    current++;
                } else if (checkNeighbor(isDeacreasingComparison, nums, current - 2, current))
                    ignore = current - 1;
            }
            current++;
        }

        if (isConsistant(isDeacreasingComparison, ignore))
            return true;
        return false;

    }

    public static boolean checkNeighbor(boolean[] isDeacreasingComparison, int[] nums, int left, int right) {
        if (left < 0 || right > nums.length - 1)
            return false;

        return (withinRange(isDeacreasingComparison, nums, left, right));
    }

    public static boolean withinRange(boolean[] isDeacreasingComparison, int[] nums, int left, int right) {

        int sum = nums[right] - nums[left];

        if (sum < 0) {
            sum = -sum;
            isDeacreasingComparison[right - 1] = true;
        }

        return (1 >= sum || sum <= 3);
    }

    public static boolean isConsistant(boolean[] isDeacreasingComparison, int ignore) {

        int changeCount = 0;
        for (int i = 1; i < isDeacreasingComparison.length; i++) {
            if (i != ignore && isDeacreasingComparison[i - 1] != isDeacreasingComparison[i]) {
                changeCount++;
            }
        }
        if (changeCount > 1)
            return false;

        return true;
    }

}

// if(nums[leftCurrent -1] < nums[leftCurrent]){
// increaseCounter++;
// }
// leftCurrent++;

// if(nums[rightCurrent] > nums[rightCurrent + 1]){
// decreaseCounter++;
// }
// rightCurrent--;

// public static boolean problemDampener(String[] parts) {

// int lastIndex = parts.length - 1;
// int[] nums = new int[parts.length];
// int sum, leftCurrent = 1, differenceIssue = 0, negativeCounter = 0;

// for (int i = 0; i <= lastIndex; i++) {
// nums[i] = Integer.parseInt(parts[i]);
// }

// while (leftCurrent <= lastIndex) {

// if (nums[leftCurrent] == nums[leftCurrent - 1]) {
// leftCurrent++;
// differenceIssue++;
// continue;
// }

// sum = nums[leftCurrent] - nums[leftCurrent - 1];

// if (sum < 0) {
// sum = -sum;
// negativeCounter--;
// } else if (sum > 0) {
// negativeCounter++;
// }

// if (sum < 1 || sum > 3) {
// differenceIssue++;
// }

// if (differenceIssue > 1)
// return false;

// leftCurrent++;

// }

// if (negativeCounter < 0)
// negativeCounter = -negativeCounter;

// if (negativeCounter + 1 < lastIndex)
// differenceIssue++;

// if (differenceIssue > 1)
// return false;

// return true;
// }
