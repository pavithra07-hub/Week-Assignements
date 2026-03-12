import java.util.*;

class Transaction {
    int id;
    int amount;

    Transaction(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}

public class Problem9 {

    public void findTwoSum(List<Transaction> list, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : list) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {

                System.out.println("Pair: " +
                        map.get(complement).id + " , " + t.id);
            }

            map.put(t.amount, t);
        }
    }

    public static void main(String[] args) {

        List<Transaction> list = new ArrayList<>();

        list.add(new Transaction(1, 500));
        list.add(new Transaction(2, 300));
        list.add(new Transaction(3, 200));

        Problem9 obj = new Problem9();

        obj.findTwoSum(list, 500);
    }
}