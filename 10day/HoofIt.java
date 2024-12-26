import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

public class HoofIt {
    static private int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public static void main(String[] args) {
        char[][] map = ReadReports.set2DArray("./10day/input.txt");
        Map<Integer, ArrayList<Integer>> visited = new TreeMap<>();
        ArrayList<int[]> starts = new ArrayList<>();
        findAllStarts(starts, map);

        iterateThroughStarts(visited, starts, map);
    }

    public static void iterateThroughStarts(Map<Integer, ArrayList<Integer>> visited, ArrayList<int[]> starts,
            char[][] map) {
        int n = map.length;
        int m = map[0].length;
        int sum = 0;

        for (int[] start : starts) {
            for (int i = 0; i < DIR.length; i++) {
                travel(visited, map, start[0], start[1], DIR[i][0], DIR[i][1], n, m);
            }

            int key = (start[0] * n) + (start[1] % m);

            sum += visited.get(key).size();

        }
        System.out.println(sum);
    }

    public static boolean travel(Map<Integer, ArrayList<Integer>> visited, char[][] map, int x, int y, int dx, int dy,
            int n,
            int m) {

        if (!isValid(visited, map, x, y, dx, dy, n, m))
            return false;

        if (map[x + dx][y + dy] == '9') {
            return true;
        }
        int key = (((x) * n) + ((y) % m));
        if (!visited.containsKey(key))
            visited.put(key, new ArrayList<>());
        boolean hasHill = false;

        for (int[] dir : DIR) {
            hasHill = travel(visited, map, x + dx, y + dy, dir[0], dir[1], n, m);

            if (hasHill) {
                int hillKey = (((x + dx + dir[0]) * n) + ((y + dy + dir[1]) % m));
                visited.get(key).add(hillKey);

            }
            int previousKey = (x + dx) * n + (y + dy) % m;
            if (visited.containsKey(previousKey)) {
                for (Integer num : visited.get(previousKey)) {
                    if (!visited.get(key).contains(num))
                        visited.get(key).add(num);
                }

            }

        }

        return false;
    }

    public static void findAllStarts(ArrayList<int[]> starts, char[][] map) {
        int currentX = 0, currentY = 0;
        boolean hasNewCharacter = true;

        while (hasNewCharacter) {
            int[] foundChracter = findCharacter(map, '0', currentX, currentY);

            if (foundChracter[0] == -1)
                hasNewCharacter = false;

            if (hasNewCharacter) {
                starts.add(foundChracter);
                currentX = foundChracter[0];
                currentY = foundChracter[1] + 1;
            }

        }
    }

    public static int[] findCharacter(char[][] map, char sym, int startX, int startY) {
        for (int i = startX; i < map.length; i++) {
            for (int j = startY; j < map.length; j++)
                if (map[i][j] == sym)
                    return new int[] { i, j };
            startY = 0;
        }

        return new int[] { -1, -1 };

    }

    public static boolean isValid(Map<Integer, ArrayList<Integer>> visited, char[][] map, int x, int y, int dx, int dy,
            int n,
            int m) {
        return isInbounds(map, x, y, dx, dy) && (map[x + dx][y + dy] - map[x][y] == 1);

    }

    public static boolean isInbounds(char[][] puzzle, int startX, int startY, int dx, int dy) {
        return (startX + dx >= 0 && startX + dx < puzzle.length && startY + dy >= 0 && startY + dy < puzzle[0].length);
    }

}