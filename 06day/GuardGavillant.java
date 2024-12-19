
import java.util.ArrayList;

public class GuardGavillant {
    final static int[][] DIRECTION = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void main(String[] args) {
        int[] indexOfGuardian;
        char[][] map = ReadReports.set2DArray("./06day/input.txt");
        ArrayList<int[]> visited = new ArrayList<>();
        int steps = 0;
        int obstacles = 0;
        indexOfGuardian = findCharacter(map);

        if (indexOfGuardian[0] == -1) {
            System.out.println("No Guardian found");
        } else {
            steps = getAmmountOfPositions(visited, map, indexOfGuardian[0], indexOfGuardian[1]);
        }

        System.out.println(steps);

        for (int[] v : visited) {
            if (stuckInLoop(map, indexOfGuardian, v[0], v[1], v[2], v[3], v[4]))
                obstacles++;
        }

        System.out.print(obstacles);

    }

    public static boolean stuckInLoop(char[][] map, int[] guardian, int x, int y, int dx, int dy,
            int turns) {
        if (guardian[0] == x && guardian[1] == y)
            return false;

        ArrayList<int[]> visited = new ArrayList<>();

        char holder = map[x][y];
        int[] object = placeObject(map, x, y, '#');

        x = guardian[0];
        y = guardian[1];
        dx = -1;
        dy = 0;
        turns = 0;
        visited.add(new int[] { x, y, dx, dy, turns });

        while (isInbounds(map, x, y, dx, dy)) {

            if (map[x + dx][y + dy] == '#') {
                turns++;
            } else {
                x += dx;
                y += dy;
            }

            dx = DIRECTION[turns % 4][0];
            dy = DIRECTION[turns % 4][1];

            int[] current = new int[] { x, y, dx, dy, turns };
            if (!hasBeenVisited(visited, current, 4))
                visited.add(current);
            else {
                object = placeObject(map, object[0], object[1], holder);
                return true;
            }
        }

        object = placeObject(map, object[0], object[1], holder);
        return false;
    }

    public static int[] placeObject(char[][] map, int x, int y, char character) {
        map[x][y] = character;
        return new int[] { x, y };
    }

    public static int getAmmountOfPositions(ArrayList<int[]> visited, char[][] map, int x, int y) {
        int turns = 0;
        int dx = DIRECTION[turns % 4][0];
        int dy = DIRECTION[turns % 4][1];
        visited.add(new int[] { x, y, dx, dy, turns });

        while (isInbounds(map, x, y, dx, dy)) {
            if (map[x + dx][y + dy] == '#') {
                turns++;
            } else {
                x += dx;
                y += dy;
            }
            dx = DIRECTION[turns % 4][0];
            dy = DIRECTION[turns % 4][1];

            int[] current = new int[] { x, y, dx, dy, turns };
            if (!hasBeenVisited(visited, current, 2))
                visited.add(current);
        }

        return visited.size();
    }

    public static boolean hasBeenVisited(ArrayList<int[]> list, int[] check, int maxIndex) {

        for (int[] array : list) {
            int matches = 0;
            for (int i = 0; i < maxIndex; i++) {
                if (array[i] != check[i])
                    break;
                else
                    matches++;
            }
            if (matches == maxIndex)
                return true;
        }

        return false;
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

    public static char[][] deepCopy(char[][] array) {
        char[][] rArr = new char[array.length][array[0].length];

        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[0].length; j++)
                rArr[i][j] = array[i][j];

        return rArr;
    }

    public static int[] findCharacter(char[][] map) {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map.length; j++)
                if (map[i][j] == '^')
                    return new int[] { i, j };

        return new int[] { -1, -1 };

    }

}