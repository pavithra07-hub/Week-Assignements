import java.util.*;

public class Problem7 {

    private HashMap<String, Integer> queryFreq = new HashMap<>();

    public void addQuery(String query) {
        queryFreq.put(query, queryFreq.getOrDefault(query, 0) + 1);
    }

    public void search(String prefix) {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for (String q : queryFreq.keySet()) {
            if (q.startsWith(prefix)) {
                pq.add(Map.entry(q, queryFreq.get(q)));
            }
        }

        int count = 0;
        while (!pq.isEmpty() && count < 10) {
            Map.Entry<String, Integer> e = pq.poll();
            System.out.println(e.getKey() + " (" + e.getValue() + " searches)");
            count++;
        }
    }

    public static void main(String[] args) {

        Problem7 auto = new Problem7();

        auto.addQuery("java tutorial");
        auto.addQuery("javascript");
        auto.addQuery("java download");
        auto.addQuery("java tutorial");

        auto.search("jav");
    }
}