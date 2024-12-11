import java.util.BitSet;
import java.util.ArrayList;

public class SumOfMinValueDifference {
    public static void main(String[] args) {
        int min = 10000, max = 99999, sum = 0;
        int arraySize = max - min + 1;
        ArrayList<int[]> nums = new ArrayList<>();
        nums = ReadReports.read("./dayOne/input.txt");
        sum = iterateThroughLine(nums, arraySize, min);
        System.out.println("After Part 2: " + sum);

    }

    public static int iterateThroughLine(ArrayList<int[]> nums, int arraySize, int min) {
        BitSet listA = new BitSet(arraySize);
        BitSet listB = new BitSet(arraySize);
        int[] occurancesA = new int[arraySize];
        int[] occurancesB = new int[arraySize];
        int left, right, sum = 0;

        for (int[] num : nums) {
            left = num[0] - min;
            right = num[1] - min;
            listA.set(left);
            listB.set(right);
            occurancesA[left]++;
            occurancesB[right]++;
        }
        sum = sumOfDifferences(listA, listB, occurancesA, occurancesB);
        System.out.println("from sum of difference method: " + sum);
        return similarityScore(listA, listB, occurancesA, occurancesB, min);

    }

    public static int similarityScore(BitSet listA, BitSet listB, int[] A, int[] B, int min) {
        int sum = 0, i = 0;
        while (i != -1) {
            i = listA.nextSetBit(i + 1);
            if (i == -1)
                continue;
            while (A[i]-- > 0)
                sum += (i + min) * B[i];
        }
        return sum;
    }

    public static int sumOfDifferences(BitSet listA, BitSet listB, int[] A, int[] B) {
        int sum = 0, total = 0;
        int i = 0, j = 0;
        int[] occA = deepCopy(A);
        int[] occB = deepCopy(B);

        while (i != -1 && j != -1) {
            i = listA.nextSetBit(i + 1);
            j = listB.nextSetBit(j + 1);
            sum = i - j;// lists have to be same size; last iteration is -1 - -1 = 0
            if (sum < 0)
                sum = -sum;
            total += sum;
            if (i > 0 && --occA[i] > 0)
                i--;
            if (j > 0 && --occB[j] > 0)
                j--;
        }
        return total;
    }

    public static int[] deepCopy(int[] array) {
        int[] rArr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            rArr[i] = array[i];
        }
        return rArr;
    }
}