import java.util.*;

class DNSEntry {
    String domain;
    String ipAddress;
    long expiryTime;

    DNSEntry(String domain, String ipAddress, int ttl) {
        this.domain = domain;
        this.ipAddress = ipAddress;
        this.expiryTime = System.currentTimeMillis() + ttl * 1000;
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

public class Problem3 {

    private HashMap<String, DNSEntry> cache = new HashMap<>();
    private int hits = 0;
    private int misses = 0;

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                hits++;
                return "Cache HIT → " + entry.ipAddress;
            } else {
                cache.remove(domain);
            }
        }

        misses++;
        String ip = queryUpstream(domain);
        cache.put(domain, new DNSEntry(domain, ip, 5));
        return "Cache MISS → " + ip;
    }

    private String queryUpstream(String domain) {
        Random r = new Random();
        return "172.217.14." + r.nextInt(255);
    }

    public void getCacheStats() {
        int total = hits + misses;
        double rate = (total == 0) ? 0 : ((double) hits / total) * 100;
        System.out.println("Hit Rate: " + rate + "%");
    }

    public static void main(String[] args) throws Exception {

        Problem3 dns = new Problem3();

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));

        Thread.sleep(6000);

        System.out.println(dns.resolve("google.com"));

        dns.getCacheStats();
    }
}