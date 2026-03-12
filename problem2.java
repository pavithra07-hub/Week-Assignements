import java.util.*;

public class Problem2 {

    // productId -> stockCount
    private HashMap<String, Integer> inventory = new HashMap<>();

    // productId -> waiting list (FIFO)
    private HashMap<String, LinkedHashMap<Integer, Integer>> waitingList = new HashMap<>();

    public Problem2() {
        inventory.put("IPHONE15_256GB", 100);
        waitingList.put("IPHONE15_256GB", new LinkedHashMap<>());
    }

    // Check stock availability
    public int checkStock(String productId) {
        return inventory.getOrDefault(productId, 0);
    }

    // Purchase item (thread-safe)
    public synchronized void purchaseItem(String productId, int userId) {

        int stock = inventory.getOrDefault(productId, 0);

        if (stock > 0) {
            inventory.put(productId, stock - 1);
            System.out.println("Success: User " + userId + " purchased " + productId +
                    ", " + (stock - 1) + " units remaining");
        } else {
            LinkedHashMap<Integer, Integer> queue = waitingList.get(productId);
            int position = queue.size() + 1;
            queue.put(userId, position);
            System.out.println("Added to waiting list: User " + userId +
                    ", position #" + position);
        }
    }

    public static void main(String[] args) {

        Problem2 system = new Problem2();

        System.out.println("Stock available: " + system.checkStock("IPHONE15_256GB") + " units");

        system.purchaseItem("IPHONE15_256GB", 12345);
        system.purchaseItem("IPHONE15_256GB", 67890);

        // simulate stock finishing
        for (int i = 0; i < 98; i++) {
            system.purchaseItem("IPHONE15_256GB", 20000 + i);
        }

        // next user goes to waiting list
        system.purchaseItem("IPHONE15_256GB", 99999);
    }
}