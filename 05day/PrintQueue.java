import java.util.ArrayList;

public class PrintQueue {

    public static void main(String[] args) {
        ArrayList<Node> nums = new ArrayList<>();
        ArrayList<String> reports = new ArrayList<>();
        ArrayList<ArrayList<Node>> nodes = new ArrayList<>();
        int[] totals = { 0, 0 };

        for (int i = 0; i < 100; i++)
            nums.add(new Node(String.valueOf(i)));

        ArrayList<String> rules = ReadReports.getRulesAndReports("./05day/input.txt", reports);
        setRules(rules, nums);
        iterateThroughReports(nodes, reports, nums, totals);

        System.out.println("Totals of good reports: " + totals[0]);
        System.out.println("Totals of fixed reports: " + totals[1]);

    }

    public static void setRules(ArrayList<String> rules, ArrayList<Node> nums) {
        for (String rule : rules) {
            Node left = Node.findNode(nums, rule.substring(0, 2));
            Node right = Node.findNode(nums, rule.substring(3, 5));
            left.addLarger(right);
            right.addSmaller(left);
        }
    }

    public static void iterateThroughReports(ArrayList<ArrayList<Node>> listOfNodes, ArrayList<String> reports,
            ArrayList<Node> nums, int[] totals) {
        int total = 0;
        for (String report : reports)
            listOfNodes.add(getNodesFromReport(nums, report));

        for (ArrayList<Node> list : listOfNodes) {
            total = valueOfValidReport(list);
            totals[0] += total;
            if (total == 0)
                totals[1] += rearrangeList(list);
        }
    }

    public static int valueOfValidReport(ArrayList<Node> list) {

        return isValid(list) ? Integer.parseInt(list.get(list.size() / 2).getValue()) : 0;
    }

    public static int rearrangeList(ArrayList<Node> list) {

        for (int i = 0; i < list.size(); i++) {

        }

        return -1;

    }

    public static boolean isValid(ArrayList<Node> list) {
        for (int i = 0; i < list.size(); i++)
            for (int j = i + 1; j < list.size(); j++)
                if (!list.get(i).isLess(list.get(j)))
                    return false;

        return true;
    }

    public static ArrayList<Node> getNodesFromReport(ArrayList<Node> list, String report) {
        ArrayList<Node> nums = new ArrayList<>();

        for (int i = 0; i < report.length(); i += 3)
            nums.add(Node.findNode(list, report.substring(i, i + 2)));

        return nums;
    }

}
