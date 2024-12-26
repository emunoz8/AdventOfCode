import java.util.ArrayList;

public class HoofIt {

    public static void main(String[] args) {
        char[][] map = ReadReports.set2DArray("./10day/input.txt");
        ArrayList<int[]> starts = new ArrayList<>();
        findAllStarts(starts, map);

        ReadReports.printMap(map);
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

    public static boolean isInbounds(char[][] puzzle, int startX, int startY, int dx, int dy) {
        return (startX + dx >= 0 && startX + dx < puzzle.length && startY + dy >= 0 && startY + dy < puzzle[0].length);
    }

}