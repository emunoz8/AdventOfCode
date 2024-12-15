import java.util.ArrayList;

public class PrintQueue {

    public static void main(String[] args) {
        ArrayList<Node> nums = new ArrayList<>();
        ArrayList<String> reports = new ArrayList<>();
        ArrayList<Node[]> nodes = new ArrayList<>();
        int total = 0;

        for (int i = 0; i < 100; i++) {
            nums.add(new Node(String.valueOf(i)));
        }

        ArrayList<String> rules = ReadReports.getRulesAndReports("./05day/input.txt", reports);
        setRules(rules, nums);

        total = iterateThroughReports(nodes, reports, nums);

        System.out.println(total);

    }

    public static void setRules(ArrayList<String> rules, ArrayList<Node> nums) {
        for (String rule : rules) {
            Node left = Node.findNode(nums, rule.substring(0, 2));
            Node right = Node.findNode(nums, rule.substring(3, 5));
            left.addLarger(right);
            right.addSmaller(left);
        }
    }

    public static int iterateThroughReports(ArrayList<Node[]> listOfNodes, ArrayList<String> reports,
            ArrayList<Node> nums) {
        int total = 0;
        for (String report : reports) {
            listOfNodes.add(getNodesFromReport(nums, report));
        }

        for (Node[] list : listOfNodes) {
            total += valueOfValidReport(list);
        }

        return total;
    }

    public static int valueOfValidReport(Node[] list) {

        for (int i = 0; i < list.length; i++) {
            for (int j = i + 1; j < list.length; j++) {
                if (!list[i].isLess(list[j]))
                    return 0;
            }
        }
        int mid = list.length / 2;

        return Integer.parseInt(list[mid].getValue());
    }

    public static Node[] getNodesFromReport(ArrayList<Node> list, String report) {
        ArrayList<String> nums = new ArrayList<>();
        Node[] rArr;

        for (int i = 0; i < report.length(); i += 3) {
            nums.add(report.substring(i, i + 2));
        }

        rArr = new Node[nums.size()];

        for (int i = 0; i < nums.size(); i++) {
            rArr[i] = Node.findNode(list, nums.get(i));
        }

        return rArr;
    }

}
