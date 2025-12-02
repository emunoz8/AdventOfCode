import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CeresSearch {
    private static AtomicInteger count = new AtomicInteger(0);

    private static final int[][] DIRECTIONS = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 },
            { 0, -1 },
            { -1, -1 } };

    private static final int[][] DIAGNOL = { { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };

    public static void main(String[] args) {

        char[][] puzzle = ReadReports.set2DArray("./04day/input.txt");

        // part 1
        ArrayList<int[]> xPositions = findAllCharacter(puzzle, 'X');

        iterateThroughX(puzzle, xPositions);

        System.out.println(count);

        // part 2
        ArrayList<int[]> aPositions = findAllCharacter(puzzle, 'A');
        int total = findXMas(puzzle, aPositions);

        System.out.println(total);

    }

    public static ArrayList<int[]> findAllCharacter(char[][] puzzle, char letter) {
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < puzzle.length; i++)
            for (int j = 0; j < puzzle[0].length; j++)
                if (puzzle[i][j] == letter)
                    list.add(new int[] { i, j });

        return list;
    }

    public static int findXMas(char[][] puzzle, ArrayList<int[]> aPositions) {
        int count = 0;
        for (int[] position : aPositions) {
            int startX = position[0];
            int startY = position[1];

            if (isValid(puzzle, startX, startY))
                count++;
        }
        return count;
    }

    public static boolean isValid(char[][] puzzle, int startX, int startY) {
        char[] clockWiseLetters = new char[4];
        int index = 0;

        for (int[] direction : DIAGNOL) {
            int dx = direction[0];
            int dy = direction[1];

            if (!isInbounds(puzzle, startX, startY, dx, dy))
                return false;
            clockWiseLetters[index++] = puzzle[startX + dx][startY + dy];

        }

        return ((clockWiseLetters[0] == 'M' && clockWiseLetters[2] == 'S'
                || clockWiseLetters[0] == 'S' && clockWiseLetters[2] == 'M')
                && (clockWiseLetters[1] == 'M' && clockWiseLetters[3] == 'S'
                        || clockWiseLetters[1] == 'S' && clockWiseLetters[3] == 'M'));

    }

    public static boolean isInbounds(char[][] puzzle, int startX, int startY, int dx, int dy) {
        return (startX + dx >= 0 && startX + dx < puzzle.length && startY + dy >= 0 && startY + dy < puzzle[0].length);
    }

    public static void iterateThroughX(char[][] puzzle, ArrayList<int[]> xPositions) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int[] position : xPositions) {
            executor.submit(() -> traverseInAllDirections(puzzle, position[0], position[1]));
        }
        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Error during thread execution: " + e);
        }
    }

    public static void traverseInAllDirections(char[][] puzzle, int startX, int startY) {

        for (int[] direction : DIRECTIONS) {
            int dx = direction[0];
            int dy = direction[1];

            findMas(puzzle, startX, startY, dx, dy);
        }
    }

    public static void findMas(char[][] puzzle, int startX, int startY, int dx, int dy) {
        int x = startX + dx;
        int y = startY + dy;
        int index = 0;
        String findWord = "MAS";
        while (isInbounds(puzzle, startX, startY, dx, dy)) {
            char current = puzzle[x][y];
            if (current != findWord.charAt(index))
                return;

            index++;
            if (index == findWord.length()) {
                count.incrementAndGet();
                return;
            }
            x += dx;
            y += dy;
        }

    }

}
