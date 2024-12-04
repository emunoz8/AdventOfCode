import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;

public class MinalueDifference {
    public static void main(String[] args) {
        // String url = "https://adventofcode.com/2024/day/1/input";
        String filePath = "./numbers.txt";
        int min = 10000, max = 99999, sum = 0;
        int arraySize = max - min + 1;
        BitSet listA = new BitSet(arraySize);
        BitSet listB = new BitSet(arraySize);
        int[] occurancesA = new int[arraySize];
        int[] occurancesB = new int[arraySize];

        // WebSaver.createTxt(url, filePath);//Had to login to get the input, maybe next
        // time
        readAndStore(filePath, listA, listB, occurancesA, occurancesB, min);

        sum = sumOfDifferences(listA, listB, occurancesA, occurancesB);

        System.out.println("Sum of differences: " + sum);

    }

    public static void readAndStore(String filePath, BitSet listA, BitSet listB, int[] occA, int[] occB, int min) {
        try {
            Files.lines(Paths.get(filePath)).forEach(line -> {
                String[] parts = line.split("\\s+", 2);
                if (parts.length == 2) {
                    int i = Integer.parseInt(parts[0]) - min;
                    int j = Integer.parseInt(parts[1]) - min;
                    listA.set(i);
                    listB.set(j);
                    occA[i]++;
                    occB[j]++;

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // sum of differences of min values
    public static int sumOfDifferences(BitSet listA, BitSet listB, int[] A, int[] B) {
        int sum = 0, total = 0;
        int i = 0, j = 0;

        while (i != -1 && j != -1) {
            i = listA.nextSetBit(i + 1);
            j = listB.nextSetBit(j + 1);

            sum = i - j;// lists have to be same size; last iteration is -1 - -1 = 0

            if (sum < 0)
                sum = -sum;

            total += sum;

            if (i > 0 && --A[i] > 0)
                i--;
            if (j > 0 && --B[j] > 0)
                j--;

        }

        return total;
    }

}
