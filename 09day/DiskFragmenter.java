import java.util.ArrayList;

public class DiskFragmenter {

    public static void main(String[] args) {
        String input = ReadReports.getString("./09day/input.txt");
        ArrayList<Integer> memory = new ArrayList<>();
        Long total;
        storeFiles(input, memory);

        defragment(memory);

        total = getCheckSum(memory);
        System.out.println(total);

        ReadReports.printMemory(memory);
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
