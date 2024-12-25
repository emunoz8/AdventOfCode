import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ResonantCollinearity {
    public static void main(String[] args) {
        char[][] map = ReadReports.set2DArray("./08day/input.txt");
        boolean[][] antinodesPostions = new boolean[map.length][map[0].length];
        Map<Character, List<int[]>> frequencies = new HashMap<>();
        int total = 0;
        getCharacterPosition(map, frequencies);
        setAntinodes(frequencies, antinodesPostions);

        total = getTotalAntinodes(antinodesPostions);

        System.out.println(total);
    }

    public static void setAntinodes(Map<Character, List<int[]>> frequencies, boolean[][] antinodesPostions) {

        for (Map.Entry<Character, List<int[]>> character : frequencies.entrySet()) {
            List<int[]> positions = character.getValue();

            for (int i = 0; i < positions.size(); i++)
                for (int j = i + 1; j < positions.size(); j++) {
                    int[] location = getLocation(antinodesPostions, positions.get(i), positions.get(j));
                    if (location[0] != -1)
                        antinodesPostions[location[0]][location[1]] = true;
                    if (location[2] != -1)
                        antinodesPostions[location[2]][location[3]] = true;

                }

        }

    }

    public static int[] getLocation(boolean[][] map, int[] left, int[] right) {
        int[] positions = { -1, -1, -1, -1 };
        int dx = left[0] - right[0];
        int dy = left[1] - right[1];

        if (isInbounds(map, left[0], left[1], dx, dy)) {
            positions[0] = left[0] + dx;
            positions[1] = left[1] + dy;
        }

        dx = right[0] - left[0];
        dy = right[1] - left[1];

        if (isInbounds(map, right[0], right[1], dx, dy)) {
            positions[2] = right[0] + dx;
            positions[3] = right[1] + dy;
        }

        return positions;
    }

    public static void getCharacterPosition(char[][] map, Map<Character, List<int[]>> frequencies) {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != '.') {
                    char key = map[i][j];
                    frequencies.putIfAbsent(key, new ArrayList<>());
                    frequencies.get(key).add(new int[] { i, j });
                }
            }
    }

    public static int getTotalAntinodes(boolean[][] antinodesPostions) {
        int sum = 0;

        for (int i = 0; i < antinodesPostions.length; i++)
            for (int j = 0; j < antinodesPostions[0].length; j++)
                sum += antinodesPostions[i][j] ? 1 : 0;

        return sum;
    }

    public static boolean isInbounds(boolean[][] puzzle, int startX, int startY, int dx, int dy) {
        return (startX + dx >= 0 && startX + dx < puzzle.length && startY + dy >= 0 && startY + dy < puzzle[0].length);
    }

}
