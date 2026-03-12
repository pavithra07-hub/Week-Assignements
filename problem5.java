import java.util.*;

public class Problem5 {

    private HashMap<String, Integer> pageViews = new HashMap<>();
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    private HashMap<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        trafficSources.put(source,
                trafficSources.getOrDefault(source, 0) + 1);
    }

    public void getDashboard() {

        System.out.println("Top Pages:");

        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(pageViews.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        int count = 0;
        for (Map.Entry<String, Integer> e : list) {

            if (count == 10)
                break;

            String url = e.getKey();
            int views = e.getValue();
            int unique = uniqueVisitors.get(url).size();

            System.out.println(url + " - " + views + " views (" + unique + " unique)");

            count++;
        }

        System.out.println("\nTraffic Sources:");

        int total = trafficSources.values().stream().mapToInt(i -> i).sum();

        for (String src : trafficSources.keySet()) {

            int val = trafficSources.get(src);
            double percent = (val * 100.0) / total;

            System.out.println(src + ": " + percent + "%");
        }
    }

    public static void main(String[] args) {

        Problem5 analytics = new Problem5();

        analytics.processEvent("/article/breaking-news", "user_123", "google");
        analytics.processEvent("/article/breaking-news", "user_456", "facebook");
        analytics.processEvent("/sports/championship", "user_111", "direct");
        analytics.processEvent("/sports/championship", "user_222", "google");
        analytics.processEvent("/sports/championship", "user_111", "google");

        analytics.getDashboard();
    }
}