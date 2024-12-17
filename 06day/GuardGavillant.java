public class GuardGavillant {

    public static void main(String[] args) {
        final int[][] DIRECTION = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        int[] indexOfGuardian;
        char[][] map = ReadReports.set2DArray("./06day/input.txt");
        int steps = 0;
        indexOfGuardian = findCharacter(map);

        if (indexOfGuardian[0] == -1) {
            System.out.println("No Guardian found");
        } else {
            steps = getAmmountOfPositions(map, DIRECTION, indexOfGuardian[0], indexOfGuardian[1]);
        }
        printMap(map);

        System.out.println(steps);
        // System.out.println(
        // indexOfGuardian[0] + " " + indexOfGuardian[1] + " " +
        // map[indexOfGuardian[0]][indexOfGuardian[1]]);

    }

    public static int getAmmountOfPositions(char[][] map, int[][] DIRECTION, int x, int y) {
        int turns = 0;
        int dx = DIRECTION[turns % 4][0];
        int dy = DIRECTION[turns % 4][1];
        int count = 1;
        map[x][y] = 'X';

        while (isInbounds(map, x, y, dx, dy)) {
            if (map[x + dx][y + dy] == '#') {
                turns++;
            } else {
                x += dx;
                y += dy;
                if (map[x][y] != 'X') {
                    count++;
                    map[x][y] = 'X';
                }
            }
            dx = DIRECTION[turns % 4][0];
            dy = DIRECTION[turns % 4][1];

        }

        return count;
    }

    public static boolean isInbounds(char[][] puzzle, int startX, int startY, int dx, int dy) {
        return (startX + dx >= 0 && startX + dx < puzzle.length && startY + dy >= 0 && startY + dy < puzzle[0].length);
    }

    public static void printMap(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }

    public static int[] findCharacter(char[][] map) {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map.length; j++)
                if (map[i][j] == '^')
                    return new int[] { i, j };

        return new int[] { -1, -1 };

    }

}