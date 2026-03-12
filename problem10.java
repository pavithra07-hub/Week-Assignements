import java.util.*;

public class Problem10 {

    private LinkedHashMap<String, String> L1 =
            new LinkedHashMap<>(10000, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<String, String> e) {
                    return size() > 10000;
                }
            };

    private HashMap<String, String> L2 = new HashMap<>();
    private HashMap<String, String> L3 = new HashMap<>();

    public Problem10() {
        L3.put("video_999", "Database Video");
    }

    public void getVideo(String id) {

        if (L1.containsKey(id)) {
            System.out.println("L1 Cache HIT");
            return;
        }

        if (L2.containsKey(id)) {
            System.out.println("L2 Cache HIT → Promoted to L1");
            L1.put(id, L2.get(id));
            return;
        }

        if (L3.containsKey(id)) {
            System.out.println("Database HIT → Added to L2");
            L2.put(id, L3.get(id));
            return;
        }

        System.out.println("Video not found");
    }

    public static void main(String[] args) {

        Problem10 cache = new Problem10();

        cache.getVideo("video_123");
        cache.getVideo("video_999");
        cache.getVideo("video_999");
    }
}