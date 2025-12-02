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

        iterateThroughStarts(visited, starts, map);// part 1
    }

    public static void iterateThroughStarts(Map<Integer, ArrayList<Integer>> visited, ArrayList<int[]> starts,
            char[][] map) {
        int n = map.length;
        int m = map[0].length;
        int sum = 0;
        int unique = 0;

        for (int[] start : starts) {
            travel(visited, map, start[0], start[1], 0, 0, n, m, true);
            int key = getKeyNum(start[0], start[1], n, m);
            sum += visited.get(key).size();
            unique += uniquePathsForEachStart(visited.get(key), map, key);

        }
        System.out.println(sum);
    }

    public static int uniquePathsForEachStart(ArrayList<Integer> hills, char[][] map, int start) {
        int total = 0;

        for(Integer hill: hills){
            total += getToHill(map, start, hill);
        }


        return total;
    }

    public static int getToHill(char[][] map, int start, int hill){
        int routes =0;

        


        return routes;
    }

    public static boolean travel(Map<Integer, ArrayList<Integer>> visited, char[][] map, int x, int y, int dx, int dy,
            int n,
            int m, boolean isFirst) {
        if (!isFirst && !isValid(visited, map, x, y, dx, dy, n, m))
            return false;

        x += dx;
        y += dy;

        int key = getKeyNum(x, y, n, m);
        if (visited.containsKey(key)) {

            return true;
        }

        visited.put(key, new ArrayList<>());
        boolean hasHill = false, atLeastOne = false;

        for (int[] dir : DIR) {

            if (map[x][y] == '9') {
                int hill = getKeyNum(x, y, n, m);
                visited.computeIfAbsent(hill, k -> new ArrayList<>()).add(hill);
                return true;
            }

            hasHill = travel(visited, map, x, y, dir[0], dir[1], n, m, false);

            if (hasHill) {
                int hillKey = getKeyNum(x + dir[0], y + dir[1], n, m);
                copyHills(visited, key, hillKey);
                atLeastOne = true;
            }

        }

        if (atLeastOne)
            return true;

        return false;
    }

    public static void copyHills(Map<Integer, ArrayList<Integer>> visited, int cKey, int pKey) {
        for (Integer num : visited.get(pKey))
            if (!visited.get(cKey).contains(num))
                visited.get(cKey).add(num);
    }

    public static int getKeyNum(int x, int y, int n, int m) {
        return (x * n) + (y % m);

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