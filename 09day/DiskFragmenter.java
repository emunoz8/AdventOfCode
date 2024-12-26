import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class DiskFragmenter {

    public static void main(String[] args) {
        String input = ReadReports.getString("./09day/input.txt");
        ArrayList<Integer> memory = new ArrayList<>();
        Map<Integer, Integer> freeMemory = new TreeMap<>();
        Map<Integer, int[]> files = new TreeMap<>(Collections.reverseOrder());
        Long total, part2;

        // part 1
        storeFiles(input, memory);
        defragment(memory);
        total = getCheckSum(memory);
        System.out.println(total);
        // ReadReports.printMemory(memory);

        // part 2
        fillTables(input, freeMemory, files);
        relocateWholeFiles(freeMemory, files);
        part2 = iterateThroughFiles(files);
        System.out.println(part2);

    }

    public static Long iterateThroughFiles(Map<Integer, int[]> files) {
        Long sum = 0L;

        for (Map.Entry<Integer, int[]> file : files.entrySet()) {
            int[] indexSize = file.getValue();
            int key = file.getKey();

            for (int i = indexSize[0]; i < indexSize[0] + indexSize[1]; i++) {
                sum += key * i;
            }

        }

        return sum;
    }

    public static void relocateWholeFiles(Map<Integer, Integer> freeMemory, Map<Integer, int[]> files) {

        for (Map.Entry<Integer, int[]> file : files.entrySet()) {
            int[] indexSize = file.getValue();
            for (Map.Entry<Integer, Integer> space : freeMemory.entrySet()) {

                if (space.getKey() > indexSize[0])
                    break;

                if (space.getValue() >= indexSize[1]) {
                    // consolidate(freeMemory, indexSize);//not necessary for problem
                    indexSize[0] = space.getKey();
                    if (space.getValue() != indexSize[1])
                        freeMemory.put((space.getKey() + indexSize[1]), space.getValue() - indexSize[1]);

                    freeMemory.remove(space.getKey());

                    break;
                }
            }
        }

    }

    public static void consolidate(Map<Integer, Integer> freeMemory, int[] free) {
        // TO-DO will add free memory to freeMemory table, in case it has adjacent free
        // memory then it will consolidate.
    }

    public static void fillTables(String input, Map<Integer, Integer> freeMemory, Map<Integer, int[]> files) {
        boolean isEven = true;
        int fileIndex = 0;
        int currentIndex = 0;

        for (int i = 0; i < input.length(); i++) {
            int c = input.charAt(i) - '0';
            if (isEven)
                files.put(fileIndex++, new int[] { currentIndex, c });
            else
                freeMemory.put(currentIndex, c);

            isEven = !isEven;
            currentIndex += c;
        }

    }

    public static Long getCheckSum(ArrayList<Integer> memory) {
        Long sum = 0L;
        for (int i = 0; i < memory.size() && memory.get(i) != null; i++)
            sum += i * memory.get(i);

        return sum;
    }

    public static void defragment(ArrayList<Integer> memory) {
        int i = 0;
        int j = memory.size() - 1;

        while (i < j) {
            while (memory.get(i) != null)
                i++;
            while (memory.get(j) == null)
                j--;
            if (i < j)
                swap(memory, i, j);
        }

    }

    public static void swap(ArrayList<Integer> memory, int i, int j) {
        Integer temp = memory.get(i);
        memory.set(i, memory.get(j));
        memory.set(j, temp);

    }

    public static void storeFiles(String input, ArrayList<Integer> memory) {
        boolean isEven = true;
        int fileIndex = 0;
        for (int i = 0; i < input.length(); i++) {
            int c = input.charAt(i) - '0';
            if (isEven) {
                for (int j = 0; j < c; j++)
                    memory.add(fileIndex);
                fileIndex++;
            } else
                for (int j = 0; j < c; j++)
                    memory.add(null);

            isEven = !isEven;

        }

    }

}
