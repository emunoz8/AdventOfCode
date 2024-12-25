
public class DiskFragmenter {

    public static void main(String[] args) {
        int[] amountOfFiles = { 0 };
        String memory = ReadReports.streamNumbers("./09day/input.txt", amountOfFiles);

        System.out.println(memory);

        System.out.println(amountOfFiles[0]);

    }

}
